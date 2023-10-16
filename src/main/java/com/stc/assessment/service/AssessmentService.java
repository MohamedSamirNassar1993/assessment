package com.stc.assessment.service;

import com.stc.assessment.exception.AssessmentException;
import com.stc.assessment.exception.ErrorKeys;
import com.stc.assessment.graphql.filter.ItemFilter;
import com.stc.assessment.mapper.FileMapper;
import com.stc.assessment.mapper.ItemMapper;
import com.stc.assessment.mapper.PermissionGroupMapper;
import com.stc.assessment.mapper.PermissionMapper;
import com.stc.assessment.model.dtos.ItemDTO;
import com.stc.assessment.model.entities.File;
import com.stc.assessment.model.entities.Item;
import com.stc.assessment.model.entities.Permission;
import com.stc.assessment.model.entities.PermissionGroup;
import com.stc.assessment.model.enums.ItemTypeEnum;
import com.stc.assessment.model.enums.PermissionLevelEnum;
import com.stc.assessment.model.payload.request.FileRequest;
import com.stc.assessment.model.payload.request.ItemRequest;
import com.stc.assessment.model.payload.response.ItemResponse;
import com.stc.assessment.repository.FileRepository;
import com.stc.assessment.repository.ItemRepository;
import com.stc.assessment.repository.PermissionGroupRepository;
import com.stc.assessment.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AssessmentService {
    private final ItemRepository itemRepository;
    private final FileRepository fileRepository;
    private final PermissionRepository permissionRepository;
    private final PermissionGroupRepository permissionGroupRepository;
    private final ItemMapper itemMapper;
    private final FileMapper fileMapper;
    private final PermissionGroupMapper permissionGroupMapper;
    private final PermissionMapper permissionMapper;


    public ItemDTO createSpace(ItemRequest itemRequest,String email) {

        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setType(ItemTypeEnum.SPACE);
        item.setCreatedBy(email);
        Optional<PermissionGroup> permissionGroupOptional = permissionGroupRepository.findByName("admin");
        if(permissionGroupOptional.isPresent()){
            item.setPermissionGroup(permissionGroupOptional.get());
        }else{
            PermissionGroup permissionGroup = new PermissionGroup();
            permissionGroup.setName("admin");
            permissionGroupRepository.save(permissionGroup);

            Permission editPermission = new Permission();
            editPermission.setUserEmail("aly@gmail.com");
            editPermission.setPermissionLevel(PermissionLevelEnum.EDIT);
            editPermission.setPermissionGroup(permissionGroup);
            permissionRepository.save(editPermission);

            Permission viewPermission = new Permission();
            viewPermission.setUserEmail("aly.mohamed@gmail.com");
            viewPermission.setPermissionLevel(PermissionLevelEnum.VIEW);
            viewPermission.setPermissionGroup(permissionGroup);
            permissionRepository.save(viewPermission);

            item.setPermissionGroup(permissionGroup);
        }
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDTO(savedItem);
    }

    public ItemDTO createFolder(ItemRequest itemRequest, String email) {

        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setType(ItemTypeEnum.FOLDER);
        item.setCreatedBy(email);
        PermissionGroup permissionGroup = permissionGroupRepository.findByName("admin").orElseThrow(() -> new AssessmentException(ErrorKeys.PERMISSION_GROUP_NOT_EXIST,HttpStatus.NOT_FOUND));
        item.setPermissionGroup(permissionGroup);
        Permission permission = permissionRepository.findByUserEmailAndPermissionGroup(email,permissionGroup).orElseThrow(() -> new AssessmentException(ErrorKeys.PERMISSION_NOT_EXIST,HttpStatus.UNAUTHORIZED));
        if(permission.getPermissionLevel() != PermissionLevelEnum.EDIT)
            throw new AssessmentException(ErrorKeys.USER_HAS_NOT_EDIT_ON_THIS_SPACE_ACCESS,HttpStatus.UNAUTHORIZED);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDTO(savedItem);
    }

    public ItemDTO createFile(FileRequest fileRequest, String email) {

        Item item = new Item();
        item.setName(fileRequest.getFile().getOriginalFilename());
        item.setType(ItemTypeEnum.FILE);
        item.setCreatedBy(email);
        PermissionGroup permissionGroup = permissionGroupRepository.findByName("admin").orElseThrow(() -> new AssessmentException(ErrorKeys.PERMISSION_GROUP_NOT_EXIST,HttpStatus.NOT_FOUND));
        item.setPermissionGroup(permissionGroup);
        Permission permission = permissionRepository.findByUserEmailAndPermissionGroup(email,permissionGroup).orElseThrow(() -> new AssessmentException(ErrorKeys.PERMISSION_NOT_EXIST,HttpStatus.UNAUTHORIZED));
        if(permission.getPermissionLevel() != PermissionLevelEnum.EDIT)
            throw new AssessmentException(ErrorKeys.USER_HAS_NOT_EDIT_ON_THIS_SPACE_ACCESS,HttpStatus.UNAUTHORIZED);
        Item savedItem = itemRepository.save(item);
        try {
            File file = new File();
            file.setFileBinary(fileRequest.getFile().getBytes());
            file.setItem(savedItem);
            file.setCreatedBy(email);
            fileRepository.save(file);
        }catch (Exception e){
            throw new AssessmentException(ErrorKeys.ERROR_IN_UPLOAD_FILE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return itemMapper.toDTO(savedItem);
    }


    public ItemResponse fetchFileMetaData(ItemFilter filter) {

        List<Permission> permissionList = permissionRepository.findByUserEmail(filter.getEmail());
        if(permissionList.isEmpty() || permissionList == null)
            throw new AssessmentException(ErrorKeys.USER_HAS_NO_PERMISSIONS_YET,HttpStatus.NOT_FOUND);
        Set<PermissionGroup> permissionGroupList = null;
        permissionList.forEach(permission -> {
            permissionGroupList.add(permission.getPermissionGroup());
        });
        if(permissionGroupList.isEmpty() || permissionGroupList == null)
            throw new AssessmentException(ErrorKeys.USER_HAS_NO_PERMISSION_GROUPS_YET,HttpStatus.NOT_FOUND);

        //List<Long> permissionGroupsIdsList = permissionGroupList.stream().map(p->p.getId()).collect(Collectors.toList());
        //List<Long> itemIdsList = itemRepository.findAllItemsByPermissionGroups(permissionGroupsIdsList);
        Set<Item> items = null;
        permissionGroupList.forEach(permissionGroup -> {
            items.addAll(permissionGroup.getItems());
        });
        if(items.isEmpty() || items == null)
            throw new AssessmentException(ErrorKeys.USER_HAS_NO_ITEMS_YET,HttpStatus.NOT_FOUND);
        List<Item> itemList = (List<Item>) items;
        if(filter.getId() != null) {
            if(itemList.stream().anyMatch(t -> t.getId() == filter.getId()))
                itemList = itemList.stream().filter(item -> filter.getId()==item.getId()&&item.getType().equals(ItemTypeEnum.FILE)).collect(Collectors.toList());
            else
                throw new AssessmentException(ErrorKeys.EMPTY_LIST_FOR_THIS_ID,HttpStatus.NOT_FOUND);
        }
        if(itemList.isEmpty() || itemList == null)
            throw new AssessmentException(ErrorKeys.EMPTY_LIST_FOR_THIS_ID,HttpStatus.NOT_FOUND);

        Page<Item> itemPage = toPage(itemList,filter);
        if(itemPage.isEmpty() || itemPage == null)
            throw new AssessmentException(ErrorKeys.EMPTY_LIST_FOR_THIS_ID,HttpStatus.NOT_FOUND);

        List<ItemDTO> itemDTOS = itemMapper.toListDTO(itemPage.getContent());

        if(itemDTOS.isEmpty() || itemDTOS==null)
            return new ItemResponse();
        ItemResponse itemResponse= new ItemResponse();
        itemResponse.setItems(itemDTOS);
        itemResponse.setTotalItems(itemPage.getTotalElements());
        itemResponse.setTotalPages(itemPage.getTotalPages());
        return itemResponse;
    }

    private Page<Item> toPage(List<Item> list, ItemFilter filter) {

        int totalPages = list.size() / filter.getLimit();
        PageRequest pageable = PageRequest.of(filter.getOffset(), filter.getLimit(),
                Sort.by(Sort.Direction.valueOf("createdDate"), "DESC"));

        int max = filter.getOffset()>=totalPages? list.size():filter.getLimit()*(filter.getOffset()+1);
        int min = filter.getOffset() >totalPages? max:filter.getLimit()*filter.getOffset();

        return new PageImpl<Item>(list.subList(min, max), pageable,
                list.size());
    }
}

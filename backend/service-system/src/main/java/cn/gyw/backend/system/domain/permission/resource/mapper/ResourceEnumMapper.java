package cn.gyw.backend.system.domain.permission.resource.mapper;

import cn.gyw.backend.system.domain.permission.resource.ResourceType;

public class ResourceEnumMapper {

    public Integer asInteger(ResourceType type) {
        return type.getCode();
    }

    public ResourceType asResourceType(Integer code) {
        return ResourceType.of(code).orElse(ResourceType.MODULE);
    }
}
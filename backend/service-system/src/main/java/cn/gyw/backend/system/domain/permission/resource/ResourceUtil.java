package cn.gyw.backend.system.domain.permission.resource;

import cn.gyw.backend.system.api.response.ResourceTree;
import cn.gyw.backend.system.domain.permission.resource.mapper.ResourceMapper;
import com.google.common.collect.Streams;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源树util
 *
 * @date 2023/9/4
 */
public class ResourceUtil {

    public static final Long ROOT_RESOURCE_ID = 0L;

    /**
     * 资源转换为树形结构
     */
    public static List<ResourceTree> resourceList2Tree(Iterable<Resource> list) {
        List<ResourceTree> parentList = Streams.stream(list)
                .filter(r -> ROOT_RESOURCE_ID.equals(r.getPid()))
                .collect(Collectors.toList())
                .stream()
                .map(ResourceMapper.INSTANCE::entityToTree)
                .collect(Collectors.toList());
        setChild(parentList, list);
        return parentList;
    }

    /**
     * 递归调用组装树形结构
     */
    private static void setChild(List<ResourceTree> parentList, Iterable<Resource> list) {
        if (CollectionUtils.isNotEmpty(parentList)) {
            parentList.forEach(pt -> {
                List<ResourceTree> nextList = Streams.stream(list)
                        .filter(r -> r.getPid().equals(pt.getId()))
                        .collect(Collectors.toList())
                        .stream()
                        .map(ResourceMapper.INSTANCE::entityToTree)
                        .collect(Collectors.toList());
                pt.setChildren(nextList);
                setChild(nextList, list);
            });
        }
    }

}

package com.example.judgmentdoc.util.tree;

import com.example.judgmentdoc.vo.TreeVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeUtil {
    public static List<TreeVO> buildTree(List<TreeVO> nodes) {
        Map<Long, List<TreeVO>> children = nodes.stream().filter(node -> node.getParentId() != 0)
                .collect(Collectors.groupingBy(node -> node.getParentId()));
        nodes.forEach(node -> node.setChildren(children.get(node.getId())));
        return nodes.stream().filter(node -> node.getParentId() == 0).collect(Collectors.toList());
    }
}

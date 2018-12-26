package com.pxkj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SysRole {

    @NonNull
    private String code;
    @NonNull
    private String name;
    private List<SysPermission> permissionList;

}

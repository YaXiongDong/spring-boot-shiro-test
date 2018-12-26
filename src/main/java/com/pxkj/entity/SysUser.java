package com.pxkj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SysUser {

    @NonNull
    private String username;
    @NonNull
    private String password;
    private List<SysRole> roleList;

}

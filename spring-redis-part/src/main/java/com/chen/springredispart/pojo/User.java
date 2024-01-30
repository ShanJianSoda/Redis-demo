package com.chen.springredispart.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//无参构造
@NoArgsConstructor
//全参构造
@AllArgsConstructor
@ToString
public class User {
    private String username;
    private String password;
}

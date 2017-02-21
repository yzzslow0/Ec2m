package com.example.ec.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yzz on 2017/2/21.
 */
@Entity
public class GreenTest {
    @Id
    private Long id;
    private String name;
    private String password;
    private String comment;
    @Generated(hash = 821552597)
    public GreenTest(Long id, String name, String password, String comment) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.comment = comment;
    }
    @Generated(hash = 1975754297)
    public GreenTest() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}

package com.blog.entity.user;

import com.blog.entity.sys.RoleEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@ApiModel(value = "用户认证实体类", description = "用户认证信息")
public class UserAuthEntity implements UserDetails {
    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("密文密码")
    private String password;

    @ApiModelProperty("用户权限列表")
    private List<RoleEntity> roles;

    /**
     * 用户权限
     *
     * @return 用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null || roles.size() <= 0) {
            return null;
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * 用户名称
     *
     * @return 用户名称
     */
    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

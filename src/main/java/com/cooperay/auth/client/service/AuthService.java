package com.cooperay.auth.client.service;

/**
* @描述: 权限认证服务接口 
* @作者： 李阳 
* @时间： 2016年6月6日 下午11:36:12 
* @版本： V1.0
 */
public interface AuthService {
    
    /**
     * 
    * @描述: 根据用户id和访问资源路径判断是否拥有该权限、
    * @返回值：拥有 true 、不拥有false 
    * @作者： 李阳
    * @时间： 2016年6月6日 下午11:37:34 
    * @版本： V1.0
     */
    public boolean auth(String resUrl , String userId);
    
    
    
    
}

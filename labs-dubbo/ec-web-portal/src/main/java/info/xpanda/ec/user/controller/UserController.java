package info.xpanda.ec.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /**
     * 列出所有用户
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String listAll(){
        return "";
    }

    /**
     * 获取一个用户
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public String getOne(){
        return "";
    }

    /**
     * 新增一个用户
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user/{id}")
    public String addOne(){
        return "";
    }

    /**
     * 更新用户的所有信息
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public String updateOneAll(){
        return "";
    }

    /**
     * 更新用户的部分信息
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/user/{id}")
    public String updateOnePart(){
        return "";
    }

    /**
     * 删除一个用户
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public String deleteOne(){
        return "";
    }
}

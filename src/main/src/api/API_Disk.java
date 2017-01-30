package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.FolderService;
import service.UserService;

/**
 * Created by Joe_C on 2017/1/29.
 */
@Controller
@RequestMapping("/api/disk")
public class API_Disk {
    @Autowired
    private FolderService folderService;
    @Autowired
    private UserService userService;
}

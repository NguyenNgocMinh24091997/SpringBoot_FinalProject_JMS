package com.trungtamjava.SpringProject.controller.admin.user;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.UserDTO;
import com.trungtamjava.SpringProject.service.UserService;

@Controller
public class AdminUserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/admin/user/search")
	public String searchUser(HttpServletRequest request,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", required = false) Integer pageNo) {
		pageNo = pageNo == null ? 1 : pageNo;
		keyword = keyword == null ? "" : keyword;

		List<UserDTO> userDTOs = userService.search(keyword, pageNo * 10, 0);
		request.setAttribute("users", userDTOs);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pageNo", pageNo);
		return "admin/users/searchUser";
	}

	@GetMapping(value = "/admin/user/detail")
	public String detailUser(HttpServletRequest request, @RequestParam("id") int id) {
		UserDTO userDTO = userService.getById(id);
		request.setAttribute("user", userDTO);
		return "admin/user/detailUser";
	}

	@GetMapping(value = "/admin/user/add")
	public String addUser() {
		return "admin/users/addUser";
	}

	@PostMapping(value = "/admin/user/add")
	public String addUser(@RequestAttribute("user") UserDTO userDTO) {
		userService.add(userDTO);
		return "redirect:/admin/user/search";
	}

	@GetMapping(value = "/admin/user/update")
	public String updateUser() {
		return "admin/user/update";
	}

	@PostMapping(value = "/admin/user/update")
	public String updateUser(@RequestParam("id") int id) {
		UserDTO userDTO = userService.getById(id);
		userService.update(userDTO);
		return "redirect:/admin/user/search";
	}

	@GetMapping(value = "/admin/user/delete")
	public String deleteUser(@RequestParam("id") int id) {
		userService.delete(id);
		return "redirect:/admin/user/delete";
	}

	@GetMapping(value = "/user/download")
	public void downloadUser(@RequestAttribute("image") String image, HttpServletResponse resp) throws IOException {
		final String UPLOAD_FOLDER = "D:\\Tech\\JMS\\SpringBoot_Final_Project\\SpringBoot_FinalProject_JMS\\SpringProject\\images\\users";
		File file = new File(UPLOAD_FOLDER + File.separator + image);
		if (file.exists()) {
			FileUtils.copyFile(file, resp.getOutputStream());
		}
	}
}

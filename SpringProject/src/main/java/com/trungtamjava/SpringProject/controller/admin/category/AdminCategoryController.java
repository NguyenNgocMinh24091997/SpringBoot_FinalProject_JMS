package com.trungtamjava.SpringProject.controller.admin.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.CategoryDTO;
import com.trungtamjava.SpringProject.service.CategoryService;

@Controller
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/admin/category/search")
	public String searchCategory(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		keyword = keyword == null ? "" : keyword;
		page = page == null ? 1 : page;

		List<CategoryDTO> categoryDTOs = categoryService.search(keyword, 0, page * 10);
		request.setAttribute("categories", categoryDTOs);
		request.setAttribute("keyword", keyword);
		request.setAttribute("page", page);
		return "admin/category/searchCategory";
	}

	@GetMapping(value = "/admin/category/detail")
	public String detailCategory(HttpServletRequest request, @RequestParam("id") int id) {
		CategoryDTO categoryDTO = categoryService.getById(id);
		request.setAttribute("category", categoryDTO);
		return "admin/category/detailCategory";
	}

	@GetMapping(value = "/admin/category/add")
	public String addCategory() {
		return "admin/category/searchCategory";
	}

	@PostMapping(value = "/admin/category/add")
	public String addCategory(HttpServletRequest request, @RequestAttribute("category") CategoryDTO categoryDTO) {
		categoryService.add(categoryDTO);
		return "redirect:/admin/category/search";
	}

	@GetMapping(value = "/admin/category/update")
	public String updateCategory() {
		return "admin/category/updateCategory";
	}

	@PostMapping(value = "/admin/category/update")
	public String updateCategory(@RequestParam("id") int id) {
		CategoryDTO categoryDTO = categoryService.getById(id);
		categoryService.update(categoryDTO);
		return "redirect:/admin/category/search";
	}

	@GetMapping(value = "/admin/category/delete")
	public String deleteCategory(@RequestParam("id") int id) {
		categoryService.delete(id);
		return "redirect:/admin/category/search";
	}
}

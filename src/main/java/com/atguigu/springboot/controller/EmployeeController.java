package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/15 13:10
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	DepartmentDao departmentDao;

	/**
	 * 查询所有员工
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/emps")
	public ModelAndView list(Model model) {

		Collection<Employee> employees = employeeDao.getAll();

		model.addAttribute("emps", employees);

		return new ModelAndView("emp/list");
	}

	//来到员工添加页面
	@GetMapping(value = "/emp")
	public ModelAndView toAddPage(Map<String, Object> map) {

		//来到添加页面，部门不能写死,要遍历出部门

		Collection<Department> departments = departmentDao.getDepartments();
		map.put("depts", departments);
		return new ModelAndView("emp/add", map);
	}

	//添加员工
	//springmvc自动将请求参数和入参对象的属性进行绑定
	@PostMapping(value = "/emp")
	public ModelAndView addEmp(Employee employee) {
		//将表单中提交的属性，封装到一个emoloyee对象中
		employeeDao.save(employee);
		//rediresct:重定向
		//forward:转发
		return new ModelAndView("redirect:/emps");
	}


	/**
	 * 来到修改页面并进行信息回显，公用同一个页面emp/add
	 *
	 * @return
	 */
	@GetMapping(value = "/emp/{id}")
	public ModelAndView modEmp(Map<String, Object> map,
							   @PathVariable("id") Integer id) {//获取请求路径的参数
		Collection<Department> departments = departmentDao.getDepartments();
		map.put("depts", departments);
		Employee employee = employeeDao.get(id);
		map.put("emp", employee);

		return new ModelAndView("emp/add", map);
	}


	/**
	 * 修改员工并放回到list页面
	 * @param employee
	 * @return
	 */
	@PutMapping(value = "/emp")
	public String updateEmp(Employee employee) {
		employeeDao.save(employee);
		System.out.println(employee);
		return "redirect:/emps";
	}
	/**
	 * 删出员工，通过id查找到员工进行删除
	 */
	@RequestMapping(value = "/emp/{id}")
	public String deleteEmp(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}
}

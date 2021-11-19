package com.yr.Serlvet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yr.Service.DepartmentService;
import com.yr.Service.EmployeeService;
import com.yr.entily.Employee;
import com.yr.entily.User;

@Controller
public class AddServlet {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	
	//ÿ������ǰ����ִ�к�@ModelAttributeһ��
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		
//		binder.setDisallowedFields("lastName");
//	} 
	
	@ModelAttribute
	public void model(Integer id,Map<String, Object> map) {
		if(id !=null) {
		map.put("employee", employeeService.get(id));
		}
	}
	
	@RequestMapping("/a")
	public String a(Locale locale,Map<String, Object> map) {
		map.put("CN", locale.toString());
		return "redirect:/list";
	}
	
	
	//���������κ��ļ�
		@RequestMapping("/download")
		public void download(HttpServletRequest request,HttpServletResponse response,String filePath) throws IOException{
			   String[] names =filePath.split("\\\\");
			   String name =names[names.length-1];
			
				File file = new File(filePath);
				//����ͷ��Ϣ
				
				response.setHeader("Content-Disposition","attachment;filename="+name);
			    response.addHeader("Content-Type","application/json;charset=UTF-8");
			    try(InputStream is = new FileInputStream(file);OutputStream os = response.getOutputStream();)
			    {
			            int read = 0;
			            byte[] bytes = new byte[2048];
			            while ((read = is.read(bytes)) != -1)
			                os.write(bytes, 0, read);
			        }
			}
	

	//ֻ������С�ļ�
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity() throws IOException{
		
		
		//˫���̰��ļ�������ǰ����������桡
		byte [] body = null;
		InputStream in = new FileInputStream(new File("C:\\Users\\86166\\Pictures\\Saved Pictures\\΢��ͼƬ_20200910230725.jpg"));
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=aa.jpg");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "json")
	public List<User> json(){
		User user =new User();
		user.setId(1);
		user.setName("text");
		user.setAddr("����");
		System.out.println(user.toString());
		
		User user1 =new User();
		user1.setId(2);
		user1.setName("text3");
		user1.setAddr("����3");
		
		List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user1);
		
		return list;
		
	}
	
	/**
	 * �޸�	
	 * @param employee
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/input",method = RequestMethod.PUT)
	public String update(Employee employee,@RequestParam("hea") MultipartFile heads) throws Exception {
		System.out.println("�޸ķ���");
		InputStream inputStream =heads.getInputStream();
		
		String path ="C:\\Users\\86166\\Desktop\\�ļ���\\ͼƬ\\qqͷ��\\";
		String name =heads.getOriginalFilename();
		path =path +name;
		System.out.println(path);
		OutputStream stream =new FileOutputStream(new File(path));
		
		int read = 0;
		byte[] bs=new byte[1024 * 1024 * 5];
		while ((read = inputStream.read(bs)) !=-1 ) {
			stream.write(bs, 0, read);
		}
			inputStream.close();
			stream.close();
		employee.setHead(path);
		employeeService.save(employee);
		return "redirect:/list";
	}
	
	/**
	 * ����
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/input/{id}",method = RequestMethod.GET)
	public String huix(@PathVariable("id")Integer id,Map<String, Object> map) {
		Map<String, Object> map2 =new HashedMap();
		map2.put("1", "��");
		map2.put("0", "Ů");
		
		map.put("map2",map2);
		map.put("employee", employeeService.get(id));
		map.put("in", departmentService.getDepartments());
		return "input";
	}
	
	
	/**
	 * ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/input/{id}",method = RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id) {
		employeeService.delete(id);
		return "redirect:/list";
	}
	
	
	/**
	 * ���
	 * @param employee
	 * @return
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = "/input",method = RequestMethod.POST)
	public String input(@Valid Employee employee,BindingResult result,Map<String, Object> map,@RequestParam("hea") MultipartFile heads) throws Exception {
		System.out.println("��ӷ���");
		System.out.println(employee);
		if(result.getErrorCount() >0) {
			System.out.println("�������ȷ��!");
			
			for(FieldError error:result.getFieldErrors()) {
				System.out.println(error.getField()+"---"+error.getDefaultMessage());
			}
			
			Map<String, Object> map2 =new HashedMap();
			map2.put("1", "��");
			map2.put("0", "Ů");
			map.put("in", departmentService.getDepartments());
			map.put("map2",map2);
			
			return "input";
		}

		InputStream inputStream =heads.getInputStream();
		
		String path ="C:\\Users\\86166\\Desktop\\�ļ���\\ͼƬ\\qqͷ��\\";
		String name =heads.getOriginalFilename();
		path =path +name;
		System.out.println(path);
		OutputStream stream =new FileOutputStream(new File(path));
		
		int read = 0;
		byte[] bs=new byte[1024 * 1024 * 5];
		while ((read = inputStream.read(bs)) !=-1 ) {
			stream.write(bs, 0, read);
		}
			inputStream.close();
			stream.close();
		
		
		employee.setHead(path);
		employeeService.save(employee);
		return "redirect:/list";
	}
	
	private OutputStream FileOutputStream(File file) {
		// TODO Auto-generated method stub
		return null;
	}




	/**
	 * ��ӻ��Ե�ѡ���ֵ��������
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/input",method = RequestMethod.GET)
    public String zf(Map<String, Object> map) {
		Map<String, Object> map2 =new HashedMap();
		map2.put("1", "��");
		map2.put("0", "Ů");
		
		map.put("in", departmentService.getDepartments());
		map.put("map2",map2);
		map.put("employee",new Employee());
		return "input";
    }
	
	/**
	 * ��ѯ
	 * @param map
	 * @return
	 */
	
	@ExceptionHandler(value = Exception.class)
	@RequestMapping("/list")
	public String list(Map<String, Object> map) {
		map.put("emp1", employeeService.getAll());
		return "list";
	}
	
	@ExceptionHandler(value =ArithmeticException.class )
	public ModelAndView eore(Exception ex) {
		
		ModelAndView andView=new ModelAndView("eroor"); 
		System.out.println("���쳣��"+ex);
		andView.addObject("exception", ex);
		return andView;
	}
	
}
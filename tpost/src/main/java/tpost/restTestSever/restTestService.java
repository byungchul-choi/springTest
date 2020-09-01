package tpost.restTestSever;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class restTestService {

	@Autowired
	restTestService restTestService;
	
	@Autowired
	restTestDao dao;

	public  List<restTestServerVO> restTest(restTestServerVO restTestServerVO) {
		// TODO Auto-generated method stub
		return dao.testSelect(restTestServerVO);
		
	}
	 
	
}

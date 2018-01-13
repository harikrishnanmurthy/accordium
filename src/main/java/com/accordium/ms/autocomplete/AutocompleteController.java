package com.accordium.ms.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accordium.ms.autocomplete.dao.ElasticUtilDao;
import com.accordium.ms.autocomplete.entity.BookStore;
import com.accordium.ms.autocomplete.entity.Title;
import com.accordium.ms.autocomplete.entity.TitleWrapper;

@Controller
public class AutocompleteController {

	@Autowired
	private ElasticUtilDao elasticUtilDao;

	@RequestMapping("/")
	public String autocomplete(Model model) {
		return "autocomplete";
	}

	@RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public TitleWrapper autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {
		System.out.println("searchstr: " + searchstr);

		List<Title> booksList = new ArrayList<>();
		List<BookStore> bookStoreList = elasticUtilDao.matchAllRecords(searchstr);
		System.out.println(bookStoreList.size());
		booksList= bookStoreList.stream().map(bk-> new Title(bk.getName())).collect(Collectors.toList());
		TitleWrapper wrapper = new TitleWrapper();
		wrapper.setSuggestions(booksList);
		return wrapper;

	}


}

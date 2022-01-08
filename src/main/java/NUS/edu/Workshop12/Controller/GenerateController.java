package NUS.edu.Workshop12.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import NUS.edu.Workshop12.exception.RandomNumberExeception;
import NUS.edu.Workshop12.model.Generate;
@Controller
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);
    //when user navigate to localhost/generate
    //Logger logger = LoggerFactory;
    

    @GetMapping("/generate") //user input url
    public String showGenerateForm(Model model){
        Generate generate = new Generate();
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        generate.setMaxNum(30);

        if(h<12){
            generate.setGreetings("Good Morning! Webpage loaded at "+cal.getTime().toString());
        }else{
            generate.setGreetings("Good Afternoon! Webpage loaded at "+cal.getTime().toString());
        }
        //generate.setNumberVal(8);
        model.addAttribute("generate", generate);
        logger.info(""+generate.getNumberVal());
        return "generate";//send out our html called generate.html
    }
    @GetMapping("/show123")//user input url
        public String showPage123(Model model){
            return "show123";
        }
    @PostMapping("/generate")//sever generate url with page
    
    public String generateNumbers(@ModelAttribute Generate generate,Model model){
        logger.info("From the form "+generate.getNumberVal() );
        int numberOfRanNum = generate.getNumberVal();
        int maxImgNum=30;
        if (numberOfRanNum>maxImgNum){
            //throw new RandomNumberException();
            model.addAttribute("errorMessage", "OMG exceed "+maxImgNum+" already !");
            return "error";
            
        }
        String[] imgNumbers={
            "number0.jpg","number1.jpg","number2.jpg","number3.jpg","number4.jpg","number5.jpg","number6.jpg","number7.jpg",
            "number8.jpg","number9.jpg","number10.jpg","number11.jpg","number12.jpg","number13.jpg","number14.jpg","number15.jpg",
            "number16.jpg","number17.jpg","number18.jpg","number19.jpg","number20.jpg", "number21.jpg","number22.jpg","number23.jpg",
            "number24.jpg","number25.jpg","number26.jpg","number27.jpg","number28.jpg","number29.jpg","number30.jpg"
        };
        List<String> selectedImg=new ArrayList<String>();
        Random randNum =new Random();
        Set<Integer> uniqueRandNum = new LinkedHashSet<Integer>();
        while(uniqueRandNum.size()<numberOfRanNum){
            Integer resultOfRandNum=randNum.nextInt(generate.getNumberVal()+1);
            uniqueRandNum.add(resultOfRandNum);
        }
        Iterator<Integer> it = uniqueRandNum.iterator();
        Integer currentElem=null;
        while(it.hasNext()){
            currentElem=it.next();
            logger.info("currentEleme > " +currentElem);
            selectedImg.add(imgNumbers[currentElem.intValue()]);

        }
        model.addAttribute("randNumResult", selectedImg.toArray());
        return "result";//return the target html page in this case is result.html
    }
}

package Postgresql;

import java.util.List;

public class DisplayQueries {

    public void display(List list){
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

}

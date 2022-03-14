package org.mrk.model;

import org.mrk.model.enums.Category;

public class OneTimeTask extends AbstractTask {

    public OneTimeTask(Category category){
      this.category = category;
    }

    public String toString(){
        return super.toString();
    }

}


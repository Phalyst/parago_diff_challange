package com.perago.techtest.services;

import com.perago.techtest.Diff;
import com.perago.techtest.DiffException;
import com.perago.techtest.DiffRenderer;

public class DiffRenderImpl implements DiffRenderer {


    @Override
    public String render(Diff<?> diff) throws DiffException {

        StringBuilder sb = new StringBuilder();
        diff.getDiffList().forEach(diffContainer ->{

            if(diffContainer.getFieldName()== null && diffContainer.getModifiedValue() != null){
                sb.append("Create "+diffContainer.getFieldName()+" as " +diffContainer.getModifiedValue()+"\n");

            }else if(diffContainer.getOriginalValue() != null && diffContainer.getModifiedValue() != null){
                sb.append("Update : "+diffContainer.getFieldName()+" from " +diffContainer.getOriginalValue()+" to "+diffContainer.getModifiedValue()+"\n");
            }
            else if(diffContainer.getOriginalValue() != null && diffContainer.getModifiedValue() == null){
                sb.append("Update : "+diffContainer.getFieldName()+" from " +diffContainer.getOriginalValue()+" to "+diffContainer.getModifiedValue()+"\n");
            }

        });

        return sb.toString();
    }
}

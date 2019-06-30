package com.perago.techtest.services;

import com.perago.techtest.Diff;
import com.perago.techtest.DiffContainer;
import com.perago.techtest.DiffEngine;
import com.perago.techtest.DiffException;
import org.apache.commons.lang3.SerializationUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DiffEngineImp implements DiffEngine {

    public <T extends Serializable> T apply(T original, Diff<?> diff) throws DiffException {

        Object newDiff =  SerializationUtils.clone(original);

        diff.getDiffList().forEach(diffCont-> {

            try {


                if(diffCont.getFieldName()!=null){
                    Field field = newDiff.getClass().getDeclaredField(diffCont.getFieldName());
                    field.setAccessible(true);
                    field.set(newDiff,diffCont.getModifiedValue());
                }

            } catch (IllegalAccessException e) {
                throw new DiffException("Cannot access fields");
            } catch (NoSuchFieldException e) {
                throw new DiffException(e.getMessage());
            }

        });

        Diff modified = new Diff();
        final List<Field> fds = getFieldsForClass(original.getClass());

        List<DiffContainer> diffList = new ArrayList<>();
        fds.forEach(fd-> {
            try {
                final DiffContainer container = calculateFieldDiff(fd, original, newDiff);

                diffList.add(container);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println(diffList);
        modified.setDiffList(diffList);

        return (T) modified ;

    }

    public <T extends Serializable> Diff<T> calculate(T original, T modified) throws DiffException {
        System.out.println("########");
        System.out.println(original);
        System.out.println(modified);

        final List<Field> fields = getFieldsForClass(original.getClass());
        Diff diff = new Diff();
        List<DiffContainer> diffList = new ArrayList<>();
        fields.forEach(f-> {
            try {
                final DiffContainer diffContainer = calculateFieldDiff(f, original, modified);
                diffList.add(diffContainer);

            } catch (IllegalAccessException e) {
                throw new DiffException("Cannot access fields");
            }
        });

        diff.setDiffList(diffList);
        return diff;
    }


    private <T extends Serializable> List<Field> getFieldsForClass(Class<T> clazz) {
        return Stream.of(clazz.getDeclaredFields())
                .collect(toList());
    }

    private DiffContainer calculateFieldDiff(Field field, Object original, Object modified) throws IllegalAccessException {
        field.setAccessible(true);
        Object originalValue = field.get(original);
        Object modifiedValue = field.get(modified);

        if(!Objects.equals(originalValue, modifiedValue)){
            return new DiffContainer(field.getName(), field.getType().cast(originalValue), field.getType().cast(modifiedValue));
        }
        return new DiffContainer();
    }

    private <T extends Serializable> void printObjectState(T obj) {
        Stream<Field> modifiedFields = Stream.of(obj.getClass().getDeclaredFields());
        modifiedFields.forEach(field -> {
            try {
                field.setAccessible(true);
                System.out.println("Field name: " + field.getName() + ", with value: " + field.get(obj) + " of type: " + field.getType().getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

}

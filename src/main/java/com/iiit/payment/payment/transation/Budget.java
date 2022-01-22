package com.iiit.payment.payment.transation;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Budget implements Payment {

    @Override
    public void save(String user, PaymentObj paymentObj, String type, String date) throws IOException {

        ArrayList<PaymentObj> paymentObjs = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<PaymentObj> info = readInfo.readBudget();

        Integer id = info.stream().map(PaymentObj::getId).collect(Collectors.toList())
                .stream().mapToInt(v->v).max().orElse(-1);

        paymentObjs.addAll(info);

        paymentObj.setUser(user);
        paymentObj.setDate(date);
        paymentObj.setId(++id);
        paymentObjs.add(paymentObj);

        SaveInfo saveInfo = new SaveInfoImpl();
        saveInfo.saveBudgetDetails(paymentObjs);

    }

    @Override
    public Boolean edit(String user, PaymentObj paymentObj, String type, Integer id) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<PaymentObj> info = readInfo.readBudget();


        Integer finalId = id;
        List<PaymentObj> obj = info.stream().filter(paymentObj1 -> paymentObj1.getId()
                .equals(finalId)).collect(Collectors.toList());

        if (obj.isEmpty())
            return false;
        else {
            info.remove(obj.get(0));
            paymentObj.setUser(obj.get(0).getUser());
            paymentObj.setDate(obj.get(0).getDate());
            paymentObj.setId(obj.get(0).getId());
            info.add(paymentObj);

            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveBudgetDetails(info);

            return true;
        }
    }

    @Override
    public Boolean delete(String user, PaymentObj paymentObj, String type, Integer id) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<PaymentObj> info = readInfo.readBudget();


        Integer finalId = id;
        List<PaymentObj> obj = info.stream().filter(paymentObj1 -> paymentObj1.getId()
                .equals(finalId)).collect(Collectors.toList());

        if (obj.isEmpty())
            return false;
        else {
            info.remove(obj.get(0));
            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveBudgetDetails(info);

            return true;
        }
    }

    @Override
    public List<PaymentObj> getPayment(String user, String category, String date, String type) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<PaymentObj> allTransaction = readInfo.readBudget();

        ArrayList<PaymentObj> returnObj = new ArrayList<>();

        if (category.equalsIgnoreCase("all")){
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)).collect(Collectors.toList()));
        }
        else {
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)
                    && paymentObj.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList()));
        }

        return returnObj;
    }
}

package com.iiit.payment.payment.transaction;

import com.iiit.payment.payment.model.Budget;
import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.model.TotalPayments;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetImpl implements Payment {

    @Override
    public void save(String user, PaymentObj paymentObj, String type, String date, LocalDateTime localDateTime) throws IOException {

        ArrayList<Budget> paymentObjs = new ArrayList<>();


        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Budget> info = readInfo.readBudget();

        Integer id = info.stream().map(Budget::getId).collect(Collectors.toList())
                .stream().mapToInt(v->v).max().orElse(-1);

        paymentObjs.addAll(info);

        paymentObj.setUser(user);
        paymentObj.setDate(date);
        paymentObj.setId(++id);
        paymentObjs.add(new Budget(paymentObj.getId(), paymentObj.getName(),
                paymentObj.getCategory(), paymentObj.getType(),
                paymentObj.getAmount(), paymentObj.getNotes(),
                paymentObj.getRecurring(), paymentObj.getUser(), paymentObj.getDate(), localDateTime));

        SaveInfo saveInfo = new SaveInfoImpl();
        saveInfo.saveBudgetDetails(paymentObjs);

    }

    @Override
    public Boolean edit(String user, PaymentObj paymentObj, String type, Integer id) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Budget> info = readInfo.readBudget();


        Integer finalId = id;
        List<Budget> obj = info.stream().filter(paymentObj1 -> paymentObj1.getId()
                .equals(finalId) && paymentObj1.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

        if (obj.isEmpty())
            return false;
        else {
            info.remove(obj.get(0));
            paymentObj.setUser(obj.get(0).getUser());
            paymentObj.setDate(obj.get(0).getDate());
            paymentObj.setId(obj.get(0).getId());
            info.add(new Budget(paymentObj.getId(), paymentObj.getName(),
                    paymentObj.getCategory(), paymentObj.getType(),
                    paymentObj.getAmount(), paymentObj.getNotes(),
                    paymentObj.getRecurring(), paymentObj.getUser(),
                    paymentObj.getDate(), obj.get(0).getDateTime()));

            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveBudgetDetails(info);

            return true;
        }
    }

    @Override
    public Boolean delete(String user, String type, Integer id) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Budget> info = readInfo.readBudget();


        Integer finalId = id;
        List<Budget> obj = info.stream().filter(paymentObj1 -> paymentObj1.getId()
                .equals(finalId) && paymentObj1.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

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
    public TotalPayments getTotalValues(String user, String date) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Budget> info = readInfo.readBudget();

        double sumValue = info.stream().mapToDouble(paymentObj -> paymentObj.getAmount()).sum();

        TotalPayments totalPayments = new TotalPayments(sumValue, 0d, 0d);

        return totalPayments;

    }

    @Override
    public List<PaymentObj> getPayment(String user, String category, String date, String type) throws IOException {

        addRecurring();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Budget> allTransaction = readInfo.readBudget();

        ArrayList<Budget> returnObj = new ArrayList<>();
        ArrayList<PaymentObj> finalObj = new ArrayList<>();

        if (category.equalsIgnoreCase("all")){
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)).collect(Collectors.toList()));
        }
        else {
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)
                    && paymentObj.getCategory().getCategoryName().equalsIgnoreCase(category)).collect(Collectors.toList()));
        }

        for (Budget ob : returnObj){

            finalObj.add(new PaymentObj(ob.getId(), ob.getName(),
                    ob.getCategory().getCategoryName(), ob.getType(), ob.getAmount(),
                    ob.getNotes(), ob.getRecurring(), ob.getUser(), ob.getDate()));

        }

        return finalObj;
    }

    private void addRecurring() throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Budget> allTransaction = readInfo.readBudget();

        LocalDateTime now = LocalDateTime.now();

        for (Budget x : allTransaction){

            if (x.getRecurring().equalsIgnoreCase("minute")) {

                LocalDateTime current = x.getDateTime();

                Duration duration = Duration.between(current,now);

                LocalDateTime future = current.plusMinutes(1);

                Boolean state = true;

                while (state) {

                    if (duration.toMinutes() > 60 || Duration.between(current.plusMinutes(1),now).toMinutes() < 1){
                        state = false;
                    }
                    else {

                        edit(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                                x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                                x.getNotes(), "none", x.getUser(), x.getDate()), "", x.getId());

                        if (Duration.between(current.plusMinutes(1),now).toMinutes() == 1) {
                            save(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                                            x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                                            x.getNotes(), "minute", x.getUser(), ""), "",
                                    String.valueOf(future.getYear()) + "-" + (future.getMonthValue() < 10 ?
                                            ("0" + String.valueOf(future.getMonthValue())) : String.valueOf(future.getMonthValue())),
                                    current.plusMinutes(1));
                        }
                        else {
                            save(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                                            x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                                            x.getNotes(), "none", x.getUser(), ""), "",
                                    String.valueOf(future.getYear()) + "-" + (future.getMonthValue() < 10 ?
                                            ("0" + String.valueOf(future.getMonthValue())) : String.valueOf(future.getMonthValue())),
                                    current.plusMinutes(1));
                        }

                        current = current.plusMinutes(1);

                    }

                }
            }
        }
    }

}

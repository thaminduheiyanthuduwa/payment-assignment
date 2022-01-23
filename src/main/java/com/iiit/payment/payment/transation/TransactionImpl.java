package com.iiit.payment.payment.transation;

import com.iiit.payment.payment.model.PaymentObj;
import com.iiit.payment.payment.model.TotalPayments;
import com.iiit.payment.payment.repositories.ReadInfo;
import com.iiit.payment.payment.repositories.SaveInfo;
import com.iiit.payment.payment.repositories.impl.ReadInfoImpl;
import com.iiit.payment.payment.repositories.impl.SaveInfoImpl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionImpl extends Transaction {

    protected TransactionImpl(CreateType createType) {
        super(createType);
    }

    @Override
    public void save(String user, PaymentObj paymentObj, String type, String date) throws IOException {

        if (paymentObj.getType().equalsIgnoreCase("income")) {

            ArrayList<Income> obj = new ArrayList<>();

            ReadInfo readInfo = new ReadInfoImpl();
            ArrayList<Income> inc = readInfo.readIncome();
            ArrayList<Expense> inc2 = readInfo.readExpenses();
            obj.addAll(inc);

            Integer id = inc.stream().map(Income::getId).collect(Collectors.toList())
                    .stream().mapToInt(v -> v).max().orElse(-1);

            Integer id2 = inc2.stream().map(Expense::getId).collect(Collectors.toList())
                    .stream().mapToInt(v -> v).max().orElse(-1);

            Integer max = Math.max(id, id2);

            TransactionImpl income = new TransactionImpl(new Income(++max, paymentObj.getName(),
                    paymentObj.getCategory(), paymentObj.getType(),
                    paymentObj.getAmount(), paymentObj.getNotes(),
                    paymentObj.getRecurring(), user, date, LocalDateTime.now()));

            obj.add((Income) income.createType);

            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveIncome(obj);

        } else {

            ArrayList<Expense> obj = new ArrayList<>();

            ReadInfo readInfo = new ReadInfoImpl();
            ArrayList<Expense> exp = readInfo.readExpenses();
            ArrayList<Income> inc = readInfo.readIncome();
            obj.addAll(exp);

            Integer id = exp.stream().map(Expense::getId).collect(Collectors.toList())
                    .stream().mapToInt(v -> v).max().orElse(-1);

            Integer id2 = inc.stream().map(Income::getId).collect(Collectors.toList())
                    .stream().mapToInt(v -> v).max().orElse(-1);

            Integer max = Math.max(id, id2);

            TransactionImpl expenses = new TransactionImpl(new Expense(++max, paymentObj.getName(),
                    paymentObj.getCategory(), paymentObj.getType(),
                    paymentObj.getAmount(), paymentObj.getNotes(),
                    paymentObj.getRecurring(), user, date, LocalDateTime.now()));

            obj.add((Expense) expenses.createType);

            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveExpenses(obj);

        }
    }

    @Override
    public Boolean edit(String user, PaymentObj paymentObj, String type, Integer id) throws IOException {


        if (paymentObj.getType().equalsIgnoreCase("income")) {

            ArrayList<Income> obj = new ArrayList<>();

            ReadInfo readInfo = new ReadInfoImpl();
            ArrayList<Income> inc = readInfo.readIncome();
            obj.addAll(inc);

            Integer finalId = id;
            List<Income> obj1 = inc.stream().filter(paymentObj1 -> paymentObj1.getId()
                    .equals(finalId) && paymentObj1.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

            if (obj1.isEmpty())
                return false;
            else {
                obj.remove(obj1.get(0));

                TransactionImpl income = new TransactionImpl(new Income(id, paymentObj.getName(),
                        paymentObj.getCategory(), paymentObj.getType(),
                        paymentObj.getAmount(), paymentObj.getNotes(),
                        paymentObj.getRecurring(), user, obj1.get(0).getDate(), obj1.get(0).getDateTime()));

                obj.add((Income) income.createType);

                SaveInfo saveInfo = new SaveInfoImpl();
                saveInfo.saveIncome(obj);

                return true;
            }
        } else {

            ArrayList<Expense> obj = new ArrayList<>();

            ReadInfo readInfo = new ReadInfoImpl();
            ArrayList<Expense> inc = readInfo.readExpenses();
            obj.addAll(inc);

            Integer finalId = id;
            List<Expense> obj1 = inc.stream().filter(paymentObj1 -> paymentObj1.getId()
                    .equals(finalId) && paymentObj1.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

            if (obj1.isEmpty())
                return false;
            else {
                obj.remove(obj1.get(0));

                TransactionImpl income = new TransactionImpl(new Expense(id, paymentObj.getName(),
                        paymentObj.getCategory(), paymentObj.getType(),
                        paymentObj.getAmount(), paymentObj.getNotes(),
                        paymentObj.getRecurring(), user, obj1.get(0).getDate(), obj1.get(0).getDateTime()));

                obj.add((Expense) income.createType);

                SaveInfo saveInfo = new SaveInfoImpl();
                saveInfo.saveExpenses(obj);

                return true;
            }

        }

    }

    @Override
    public Boolean delete(String user, String type, Integer id) throws IOException {


        ArrayList<Income> obj = new ArrayList<>();
        ArrayList<Expense> obj2 = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Income> inc = readInfo.readIncome();
        ArrayList<Expense> inc2 = readInfo.readExpenses();
        obj.addAll(inc);
        obj2.addAll(inc2);

        Integer finalId = id;
        List<Income> obj1 = inc.stream().filter(paymentObj1 -> paymentObj1.getId()
                .equals(finalId) && paymentObj1.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

        List<Expense> obj3 = inc2.stream().filter(paymentObj1 -> paymentObj1.getId()
                .equals(finalId) && paymentObj1.getUser().equalsIgnoreCase(user)).collect(Collectors.toList());

        if (obj1.isEmpty() && obj3.isEmpty())
            return false;
        else if (!obj1.isEmpty()){
            obj.remove(obj1.get(0));

            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveIncome(obj);

            return true;
        }
        else if (!obj3.isEmpty()){
            obj2.remove(obj3.get(0));

            SaveInfo saveInfo = new SaveInfoImpl();
            saveInfo.saveExpenses(obj2);

            return true;
        }
        else
            return false;

    }

    @Override
    public TotalPayments getTotalValues(String user, String date) throws IOException {

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Income> inc = readInfo.readIncome();
        ArrayList<Expense> inc2 = readInfo.readExpenses();

        double income = inc.stream().mapToDouble(paymentObj -> paymentObj.getAmount()).sum();

        double expense = inc2.stream().mapToDouble(paymentObj -> paymentObj.getAmount()).sum();

        TotalPayments totalPayments = new TotalPayments(income+expense, income, expense);

        return totalPayments;

    }

    @Override
    public List<PaymentObj> getPayment(String user, String category, String date, String type) throws IOException {

        addRecurring();

        ArrayList<Income> obj = new ArrayList<>();
        ArrayList<Expense> obj2 = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Income> inc = readInfo.readIncome();
        ArrayList<Expense> inc2 = readInfo.readExpenses();
        obj.addAll(inc);
        obj2.addAll(inc2);

        List<PaymentObj> allTransaction = new ArrayList<>();

        for (Income x : obj){
            allTransaction.add(new PaymentObj(x.getId(), x.getName(), x.getCategory().getCategoryName()
            , x.getType(), x.getAmount(), x.getNotes(),
                    x.getRecurring(), x.getUser(), x.getDate()));
        }

        for (Expense x : obj2){
            allTransaction.add(new PaymentObj(x.getId(), x.getName(), x.getCategory().getCategoryName()
            , x.getType(), x.getAmount(), x.getNotes(),
                    x.getRecurring(), x.getUser(), x.getDate()));
        }

        ArrayList<PaymentObj> returnObj = new ArrayList<>();

        if (category.equalsIgnoreCase("all") && type.equalsIgnoreCase("all")) {
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)).collect(Collectors.toList()));
        } else if (category.equalsIgnoreCase("all") && !type.equalsIgnoreCase("all")) {
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)
                    && paymentObj.getType().equalsIgnoreCase(type)).collect(Collectors.toList()));
        } else if (!category.equalsIgnoreCase("all") && type.equalsIgnoreCase("all")) {
            returnObj.addAll(allTransaction.stream().filter(paymentObj -> paymentObj.getDate().equalsIgnoreCase(date)
                    && paymentObj.getUser().equalsIgnoreCase(user)
                    && paymentObj.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList()));
        }

        return returnObj;
    }

    private void addRecurring() throws IOException {

        ArrayList<Income> obj = new ArrayList<>();
        ArrayList<Expense> obj2 = new ArrayList<>();

        ReadInfo readInfo = new ReadInfoImpl();
        ArrayList<Income> inc = readInfo.readIncome();
        ArrayList<Expense> inc2 = readInfo.readExpenses();
        obj.addAll(inc);
        obj2.addAll(inc2);

        LocalDateTime now = LocalDateTime.now();

        for (Income x : obj){

            if (x.getRecurring().equalsIgnoreCase("minute")) {

                LocalDateTime current = x.getDateTime();

                Duration duration = Duration.between(current,now);

                LocalDateTime future = current.plusMinutes(1);

                if (duration.toMinutes() < 60 && Duration.between(current.plusMinutes(1),now).toMinutes() >  -1){

                    edit(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                            x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                            x.getNotes(), "none", x.getUser(), x.getDate()), "", x.getId());

                    save(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                            x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                            x.getNotes(), "minute", x.getUser(), ""), "",
                            String.valueOf(future.getYear())+"-"+ (future.getMonthValue()<10 ?
                                    ("0"+String.valueOf(future.getMonthValue())):String.valueOf(future.getMonthValue())));

                }
            }
        }
        for (Expense x : obj2){

            if (x.getRecurring().equalsIgnoreCase("minute")) {

                Duration duration = Duration.between(x.getDateTime(),now);

                LocalDateTime future = now.plusMinutes(1);

                if (duration.toMinutes() < 60 && Duration.between(future,now).toMinutes() <  1){

                    edit(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                            x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                            x.getNotes(), "none", x.getUser(), x.getDate()), "", x.getId());

                    save(x.getUser(), new PaymentObj(x.getId(), x.getName(),
                                    x.getCategory().getCategoryName(), x.getType(), x.getAmount(),
                                    x.getNotes(), "minute", x.getUser(), ""), "",
                            String.valueOf(future.getYear())+"-"+ (future.getMonthValue()<10 ?
                                    ("0"+String.valueOf(future.getMonthValue())):String.valueOf(future.getMonthValue())));

                }
            }

        }

    }

}

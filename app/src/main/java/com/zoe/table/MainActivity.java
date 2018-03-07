package com.zoe.table;

import android.app.Activity;
import android.os.Bundle;

import com.zoe.table.bean.UserModel;
import com.zoe.table.view.TableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<UserModel> list = new ArrayList<>();
        list.add(new UserModel("章子怡","18"));
        list.add(new UserModel("李连杰","28"));
        list.add(new UserModel("成龙","56"));
        list.add(new UserModel("周杰伦","30"));
        list.add(new UserModel("欧阳娜娜","18"));
        list.add(new UserModel("杰森.斯坦斯","45"));
        list.add(new UserModel("奥巴马","70"));
        TableView tv = (TableView)findViewById(R.id.my_table_view);
        tv.setData(list);
    }
}

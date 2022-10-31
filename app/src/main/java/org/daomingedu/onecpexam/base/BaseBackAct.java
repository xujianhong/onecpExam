package org.daomingedu.onecpexam.base;


import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


import org.daomingedu.onecpexam.R;

import androidx.appcompat.widget.Toolbar;


/**
 * Created by Administrator on 2016/6/27.
 */
public abstract class BaseBackAct extends BaseAct {



    public Toolbar toolbar;


    /**
     * 不添加右侧按钮传此参数
     */
    public static final int NOADDRIGHTBTN = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**是否显示右边添加按钮*/
    protected Toolbar addMenue(int menuID){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(menuID);
        return toolbar;
    }
    protected void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }

    /**
     * 左侧按钮点击事件
     * @return toolbar
     */
    public Toolbar addNavBtn() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.mipmap.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.back();
            }
        });

        return toolbar;
    }

    /**
     * 添加标题栏的按钮，默认添加左侧返回按钮 如果要显示左侧按钮必须调用此方法
     * @param menuID   右侧按钮（可多个） 不添加按钮传 NOADDRIGHTBTN
     * @return
     */
    public Toolbar addBtn(int menuID) {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.back();
            }
        });
        if(menuID != NOADDRIGHTBTN) {
            toolbar.inflateMenu(menuID);
            return toolbar;
        }
        return null;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            bd.back();
            return true;
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}

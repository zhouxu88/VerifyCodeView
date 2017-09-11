package com.zx.verifycodeview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 作者： 周旭 on 2017年9月11日 0011.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class VerifyCodeView extends RelativeLayout {
    private EditText editText;
    private TextView[] textViews; //显示验证码的数组
    private static int MAX = 6; //验证码的长度为6位
    private String inputContent; //edittext的输入内容

    public VerifyCodeView(Context context) {
        this(context, null);
    }

    public VerifyCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.verify_code_view, this);

        textViews = new TextView[MAX];
        textViews[0] = (TextView) findViewById(R.id.item_code_iv0);
        textViews[1] = (TextView) findViewById(R.id.item_code_iv1);
        textViews[2] = (TextView) findViewById(R.id.item_code_iv2);
        textViews[3] = (TextView) findViewById(R.id.item_code_iv3);
        textViews[4] = (TextView) findViewById(R.id.item_code_iv4);
        textViews[5] = (TextView) findViewById(R.id.item_code_iv5);
        editText = (EditText) findViewById(R.id.item_edittext);

        setEditTextListener();
    }

    private void setEditTextListener() {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = editText.getText().toString().trim();
                if (inputCompleteListener != null) {
                    if (inputContent.length() >= MAX) {
                        inputCompleteListener.inputComplete();
                    } else {
                        inputCompleteListener.invalidContent();
                    }
                }

                for (int i = 0; i < MAX; i++) {
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    } else {
                        textViews[i].setText("");
                    }
                }
            }
        });
    }


    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    /**
     * 输入完成的监听
     */
    public interface InputCompleteListener {

        void inputComplete(); //输入完成

        void invalidContent(); //输入内容无效
    }

    public String getEditContent() {
        return inputContent;
    }

}

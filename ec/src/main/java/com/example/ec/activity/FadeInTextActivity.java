package com.example.ec.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.easycode.view.text.FadeInText;
import com.example.ec.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FadeInTextActivity extends AppCompatActivity {

    @BindView(R.id.fadeintext)
    FadeInText fadeintext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_in_text);
        ButterKnife.bind(this);

        fadeintext.setTextAnimationListener(new FadeInText.TextAnimationListener() {
            @Override
            public void animationFinish() {

            }
        });

        fadeintext.setTextString("毛主席说：" +
                "\n1、枪杆子里面出政权！" +
                "\n2、星星之火，可以燎原！" +
                "\n3、一切反动派都是纸老虎！" +
                "\n4、这只是万里长征的第一步！" +
                "\n5、中国人民从此站起来了！" +
                "\n6、人不犯我，我不犯人。" +
                "\n7、天要下雨，娘要嫁人，由他去吧！" +
                "\n8、你办事，我放心。" +
                "\n9、自己动手，丰衣足食！" +
                "\n10、为人民服务！" +
                "\n11、只有落后的领导，没有落后的群众！群众的眼睛是亮的！" +
                "\n12、中国人不怕原子弹，死一半也没什么，照样接着搞社会主义。");
        fadeintext.startFadeInAnimation();
    }
}

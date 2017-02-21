package com.example.ec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzz on 2017/2/21.
 */

public class DataServer {

    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "CymChad";

    private DataServer() {
    }

    public static List<StateHEHE> getSampleData(int lenth) {
        List<StateHEHE> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            StateHEHE status = new StateHEHE();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

}
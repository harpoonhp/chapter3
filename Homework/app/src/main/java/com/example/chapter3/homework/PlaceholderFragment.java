package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlaceholderFragment extends Fragment {

    ListView list;
    String[] mName = { "item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8" };
    ArrayList<Map<String, Object>> mData = new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
            View view = inflater.inflate(R.layout.fragment_placeholder, null);
            list = view.findViewById(R.id.lvItems);
            for(int i = 0; i < mName.length; i ++){
                Map<String,Object> item = new HashMap();
                item.put("name", mName[i]);
                mData.add(item);
            }
            SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,android.R.layout.simple_expandable_list_item_1,
                    new String[]{"name"},new int[]{android.R.id.text1});
            list.setAdapter(adapter);
            return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                View animationview1 = getView().findViewById(R.id.animation_view1);
                View lvItems = getView().findViewById(R.id.lvItems);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(animationview1,"alpha",1,0);
                objectAnimator1.setDuration(1000);
                //objectAnimator1.setInterpolator(new LinearInterpolator());
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(lvItems,"alpha",0,1);
                objectAnimator2.setDuration(1000);
                //objectAnimator2.setInterpolator(new LinearInterpolator());
                list.setVisibility(View.VISIBLE);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator1,objectAnimator2);
                animatorSet.start();
            }
        }, 5000);
    }
}




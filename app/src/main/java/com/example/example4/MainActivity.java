package com.example.example4;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends Activity {
    private ListView listview;
    SimpleAdapter adapter;
    int [] imageId = new int[]{R.mipmap.timg,R.mipmap.timg,R.mipmap.timg,R.mipmap.timg,R.mipmap.timg,R.mipmap.timg};
    String[] title = new String[]{"One","Two","Three","Four","Five","Six"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listView2);
        //创建list集合
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0;i<imageId.length;i++)

        {
            //实例化Map对象
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            //将map对象添加到List集合
            listItems.add(map);
        }
        adapter = new SimpleAdapter(this, listItems, R.layout.items2, new String[]{"title","image"},new int[]{R.id.title,R.id.image});
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        ListView.MultiChoiceModeListener callback = new MyMultiChoiceModeListener();
        listview.setMultiChoiceModeListener(callback);
        listview.setAdapter(adapter);
    }

    private class MyMultiChoiceModeListener implements ListView.MultiChoiceModeListener {

        boolean isFirst = true;
        private TextView mSelectedCount;
        private View mMultiSelectActionBarView;
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_2, menu);
            if (mMultiSelectActionBarView == null) {
                mMultiSelectActionBarView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.list_multi_select_actionbar, null);

                mSelectedCount = (TextView) mMultiSelectActionBarView.findViewById(R.id.selected_conv_count);
                ((TextView)mMultiSelectActionBarView.findViewById(R.id.title)).setText("selected");

            }
            mode.setCustomView(mMultiSelectActionBarView);//设置新的ActionBar样式
            return true;
        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            if(isFirst){
                mSelectedCount.setText("1");
                isFirst = false;
            }else{
                mSelectedCount.setText(listview.getCheckedItemCount()+"");
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }


    }

}

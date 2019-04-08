list_multi_select_actionbar.xml

  <?xml version="1.0" encoding="utf-8"?>
  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    
    android:layout_width="match_parent"
    
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/selected_conv_count"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="26dp"
        />
    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="26dp"
        />
</LinearLayout>


activity_main.xml

  <?xml version="1.0" encoding="utf-8"?>
  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    
    android:orientation="vertical"
    
    android:layout_width="fill_parent"
    
    android:layout_height="fill_parent"
    android:background="@drawable/activated"
    >
    <ListView
        android:id="@+id/listView2"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </ListView>

</LinearLayout>

mainactivity

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

结果截图：https://github.com/linyu0119/UIexample4/blob/master/images/1.jpg

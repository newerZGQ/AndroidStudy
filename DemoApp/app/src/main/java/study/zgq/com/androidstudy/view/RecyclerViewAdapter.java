package study.zgq.com.androidstudy.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.activity.Main6Activity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.VH> {
    private ArrayList<Main6Activity.Data> data;
    public RecyclerViewAdapter(ArrayList<Main6Activity.Data> data){
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("nanwei", "onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        Log.d("nanwei", "onBindViewHolder");
        int realPos = i % data.size();
        ((TextView)vh.textView).setText(""+data.get(realPos).a);
        ((TextView)vh.textView1).setText(""+data.get(realPos).b);
        vh.textView.setTextSize(30);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        Log.d("nanwei", "onBindViewHolder payload");
//        int realPos = position % data.size();
        if(payloads.isEmpty()){
            onBindViewHolder(holder, position);
        }else{
            holder.textView.setText(payloads.get(0).toString());
        }
    }

    @Override
    public int getItemCount() {
        Log.d("nanwei", "getItemCount");
        return data.size();
    }

    public static class VH extends RecyclerView.ViewHolder{

        private TextView textView;
        private TextView textView1;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recy_item);
            textView1 = itemView.findViewById(R.id.recy_item_1);
        }
    }
}

package cn.wildfire.chat.app.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import cn.wildfirechat.chat.R;

public class ProgressUtils {
    private static Dialog mLoadingDialog = null;
    private static ProgressUtils mInstance = null;
    AVLoadingIndicatorView loadingView;
    public static ProgressUtils getInstance() {
        if (mInstance == null) {
            mInstance = new ProgressUtils();

        }
        return mInstance;
    }
    public void show(Activity activity){
        //show(activity,"请稍等...");
        show(activity, R.string.loading);
    }
    public void show(Activity activity,int text){
        try {
            if (activity == null) {
                return;
            }
            if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
                return;
            }

                mLoadingDialog = new Dialog(activity, R.style.loading_dialog_style);
                mLoadingDialog.setCanceledOnTouchOutside(false);
                View view = LayoutInflater.from(activity).inflate(R.layout.layout_loading_dialog, null);
            TextView loadingText =view.findViewById(R.id.loading_dialog_text);
//            if(TextUtils.isEmpty(text)){
//                loadingText.setVisibility(View.GONE);
//            }else{
                loadingText.setText(text);
                loadingText.setVisibility(View.VISIBLE);
           // }

             loadingView = (AVLoadingIndicatorView) view.findViewById(R.id.av_loading);


            // app:indicatorName="PacmanIndicator"
           // loadingView.setIndicator("BallSpinFadeLoaderIndicator");
            loadingView.setIndicator("LineScalePulseOutIndicator");


            mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));

            mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    loadingView=null;
                    mLoadingDialog=null;
                }
            });

            if (!activity.isFinishing()) {
                mLoadingDialog.show();
                loadingView.smoothToShow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismiss() {
        try {
            if (mLoadingDialog != null) {
                loadingView.smoothToHide();
                mLoadingDialog.dismiss();
                loadingView = null;
                mLoadingDialog = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

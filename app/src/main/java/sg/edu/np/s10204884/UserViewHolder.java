package sg.edu.np.s10204884;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView Name;
    public TextView Description;
    public ImageView AndroidImg;
    public View view;
    public UserViewHolder( View itemView) {
        super(itemView);
        AndroidImg = itemView.findViewById(R.id.img_profile);
        Name = itemView.findViewById(R.id.userName);
        Description = itemView.findViewById(R.id.userDescription);

        view = itemView;

    }

}

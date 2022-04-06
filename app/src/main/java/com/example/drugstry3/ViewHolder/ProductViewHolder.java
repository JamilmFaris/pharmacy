package com.example.drugstry3.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugstry3.Model.Product;
import com.example.drugstry3.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    TextView name, price;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.product_name);
        price = itemView.findViewById(R.id.product_price);
    }
    public void bind(Product product){
        name.setText(product.prodName);
        price.setText(product.price);
    }
}

//package ies.tsds.letscooks;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
//    private Context context;
//    private List<Recipe> recipeList;
//
//    public RecipeAdapter(Context context, List<Recipe> recipeList) {
//        this.context = context;
//        this.recipeList = recipeList;
//    }
//
//    @NonNull
//    @Override
//    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
//        return new RecipeViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
//        Recipe recipe = recipeList.get(position);
//        holder.nombreReceta.setText(recipe.getName());
//        holder.recipeImage.setImageResource(recipe.getImage());
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, DetalleRecetasActivity.class);
//            intent.putExtra("recipe_id", recipe.getId());
//            Log.d("RecipeAdapter", "ID de receta enviada: " + recipe.getId()); // Agregar log
//            context.startActivity(intent);
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return recipeList.size();
//    }
//
//    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
//        TextView nombreReceta;
//        ImageView recipeImage;
//
//        public RecipeViewHolder(View itemView) {
//            super(itemView);
//            nombreReceta = itemView.findViewById(R.id.nombre_receta);
//            recipeImage = itemView.findViewById(R.id.recipe_image);
//        }
//    }
//}

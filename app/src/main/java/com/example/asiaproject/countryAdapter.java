package com.example.asiaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class countryAdapter extends RecyclerView.Adapter<countryAdapter.countryViewHolder> {

    Context context;
    List<countries> countries;

    public countryAdapter(Context context , List<countries> results){
        this.context = context;
        this.countries = results;
    }


    @Override
    public countryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new countryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(countryViewHolder holder, int position) {
        holder.Bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class countryViewHolder extends RecyclerView.ViewHolder {

        countryViewHolder(View itemview){
            super(itemview);
        }

        public void Bind(countries countryInfo){

            TextView name = itemView.findViewById(R.id.name);
            name.setText("Name : " + countryInfo.name);
            TextView capital = itemView.findViewById(R.id.Capital);
            capital.setText("Capital : " + countryInfo.capital);
            TextView region = itemView.findViewById(R.id.Region);
            region.setText("Agency : " + countryInfo.region);
            TextView subRegion = itemView.findViewById(R.id.subRegion);
            subRegion.setText("SubRegion : " + countryInfo.subregion);
            ImageView picture = itemView.findViewById(R.id.poster_image);
            TextView population = itemView.findViewById(R.id.population);
            population.setText("Population : " + countryInfo.population);

            TextView language = itemView.findViewById(R.id.languages);
            String str = "Languages : ";
            for(int i = 0; i<countryInfo.languages.size(); i++){
                str = str + " '" + ((LanguagesItem)(countryInfo.languages).get(i)).name + "'";
            }
            language.setText(str);

            TextView borders = itemView.findViewById(R.id.borders);
            str = "Borders : ";
            for (int i = 0; i<countryInfo.borders.size(); i++){
              str = str + " '" + (countryInfo.borders).get(i) + "'";
            }
            borders.setText(str);

            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(countryInfo.flag)
                    .placeholder(R.drawable.loading)
                    .fit()
                    .into(picture);
        }
    }

    public void getAllCountries(List<countries> countriesList)
    {
        this.countries = countriesList;
    }
}

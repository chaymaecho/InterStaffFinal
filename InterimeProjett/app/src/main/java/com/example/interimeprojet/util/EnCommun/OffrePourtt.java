package com.example.interimeprojet.util.EnCommun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interimeprojet.R;
import com.example.interimeprojet.models.Offres;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class OffrePourtt extends FirebaseRecyclerAdapter<Offres, OffrePourtt.PublishedVacancyHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OffrePourtt(@NonNull FirebaseRecyclerOptions<Offres> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull PublishedVacancyHolder holder, final int position, @NonNull Offres model) {
        holder.title.setText("Profession. "+model.getJobTitle());
        holder.description.setText("Plus de détail. "+model.getDescription());
        holder.salary.setText("Salaire. " + model.getSalary() + " Є/mois");
        holder.organization.setText("Societé. " + model.getOrganization());
        String temp = model.getDeadline();
        holder.date.setText("Date limite :"+temp);



    }

    @NonNull
    @Override
    public PublishedVacancyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidat_offre, parent, false);

        return new PublishedVacancyHolder(view);
    }

    class PublishedVacancyHolder extends RecyclerView.ViewHolder {

        TextView title, description, salary, organization,date;
        Button btnUpdate, btnDelete;

        public PublishedVacancyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvUpdateJobTitle);
            description = itemView.findViewById(R.id.tvUpdateJobDescription);
            salary = itemView.findViewById(R.id.tvUpdateJobSalary);
            organization =itemView.findViewById(R.id.tvUpdateJoborg);
            date = itemView.findViewById(R.id.tvUpdateJobDate);
        }
    }
}

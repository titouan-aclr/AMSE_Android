package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class AnnoncesListe extends AppCompatActivity {

    public ArrayList<AdModel> listedAnnonces = new ArrayList<>();
    RecyclerView recyclerView;
    private static RecyclerViewAdAdapter adAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private Menu menu;
    private boolean isListView = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_liste);

        recyclerView = findViewById(R.id.recyclerView);

        createList();

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        adAdapter = new RecyclerViewAdAdapter(listedAnnonces);
        recyclerView.setAdapter(adAdapter);

        adAdapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(AnnoncesListe.this,DetailsActivity.class);
                intent.putExtra("MODEL", listedAnnonces.get(position));
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.actionChangeDisplayMode:
                changeDisplayMode();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeDisplayMode() {
        if(isListView){
            adAdapter.setIsListView(false);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adAdapter);
            isListView = false;
            menu.getItem(0).setIcon(R.drawable.baseline_view_list_24);
        } else {
            adAdapter.setIsListView(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adAdapter);
            isListView = true;
            menu.getItem(0).setIcon(R.drawable.baseline_grid_view_24);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }
    
    void createList(){
        listedAnnonces.add(new AdModel("Citroën SM", 49800.0, R.drawable.citroen_sm, 1970, "0320335571", "test1@gmail.com", "À vendre : Citroën SM de 1970, une berline de luxe emblématique des années 70. Dotée d'un moteur V6 Maserati, cette voiture arbore un design distinctif et futuriste, avec sa suspension hydropneumatique révolutionnaire. Kilométrage : 85 000 km. Intérieur en cuir bien entretenu, peinture d'origine en bon état. Prix négociable dans la limite du raisonnable. Une opportunité rare pour les amateurs de voitures classiques."));
        listedAnnonces.add(new AdModel("Range Rover", 57500.0, R.drawable.range_rover, 1970, "À saisir : Range Rover Classic de 1970, un véhicule tout-terrain emblématique qui incarne le luxe et la robustesse. Ce modèle particulier est équipé du puissant moteur V8 de 3,5 litres, parfait pour affronter tous les terrains. Avec seulement 70 000 km au compteur, cet exemplaire bénéficie d'un entretien méticuleux et d'une restauration récente. Intérieur spacieux en cuir, carrosserie en excellent état. Prix compétitif et possibilité de discuter. Une occasion unique de posséder un morceau d'histoire automobile."));
        listedAnnonces.add(new AdModel("Autobianchi A112 Abarth", 14900.0, R.drawable.autobianchi_a112_abarth, 1971, "0320335573",null, "À vendre : Autobianchi A112 Abarth de 1971. Performante et agile, équipée d'un moteur 4 cylindres Abarth. En bon état général, avec environ 95 000 km au compteur. Intérieur préservé, carrosserie soignée. Prix négociable. Parfaite pour les amateurs de conduite sportive des années 70."));
        listedAnnonces.add(new AdModel("Alpine A310", 38900.0, R.drawable.alpine_a310, 1971, null,"test5@gmail.com","À vendre : Alpine A310 de 1971, une voiture de sport emblématique des années 70. Équipée d'un moteur V6 en position centrale, offrant des performances exceptionnelles. Ce modèle spécifique affiche 80 000 km au compteur et a été soigneusement entretenu. Intérieur en bon état, carrosserie avec quelques traces d'usure. Prix à discuter dans la limite du raisonnable. Une occasion unique de posséder un morceau de l'histoire automobile française."));
        listedAnnonces.add(new AdModel("Fiat 130 Coupé", 11900.0, R.drawable.fiat_130_coupe, 1971, "0320335575","test5@gmail.com", "À vendre : Fiat 130 Coupé de 1971, une élégante voiture de grand tourisme des années 70. Propulsée par un moteur V6, cette Fiat offre une conduite souple et confortable. Affichant un kilométrage de 90 000 km, elle a été méticuleusement entretenue. L'intérieur spacieux et luxueux est en excellent état, tout comme la carrosserie. Prix compétitif et possibilité de négociation. Une pièce rare pour les amateurs de voitures classiques italiennes."));
        listedAnnonces.add(new AdModel("Alfa Romeo Alfasud", 12900.0, R.drawable.alfasud, 1972,  "À vendre : Alfa Romeo Alfasud de 1972, une berline compacte dynamique et pleine de caractère. Dotée d'un moteur boxer à quatre cylindres, cette Alfasud offre une conduite agile et sportive. Avec seulement 65 000 km au compteur, elle présente un intérieur bien préservé et une carrosserie en bon état. Prix attractif et ouvert à la négociation. Une opportunité idéale pour les passionnés de voitures italiennes classiques."));
        listedAnnonces.add(new AdModel("Renault 5", 9500.0, R.drawable.renault_5, 1972, "0320335577","test7@gmail.com", "À vendre : Renault 5 de 1972, une icône des voitures compactes des années 70. Propulsée par un moteur économique et fiable, cette Renault 5 offre une conduite urbaine plaisante. Avec un kilométrage de 80 000 km, elle a été bien entretenue et bénéficie d'un intérieur propre. La carrosserie présente quelques signes d'usure normale pour son âge. Prix abordable et possibilité de négociation. Parfaite pour les amateurs de voitures rétro et de style français."));
        listedAnnonces.add(new AdModel("Lancia Stratos", 373750.0, R.drawable.lancia_stratos, 1973, "0320335578","test8@gmail.com", "À vendre : Lancia Stratos de 1973, une légende de la compétition automobile des années 70. Propulsée par un moteur V6 Ferrari, cette voiture de rallye emblématique offre des performances époustouflantes. Avec seulement 50 000 km au compteur, elle est en excellent état, aussi bien à l'intérieur qu'à l'extérieur. Prix sur demande pour les collectionneurs sérieux. Une occasion rare de posséder un morceau d'histoire de la course automobile."));
        listedAnnonces.add(new AdModel("Matra Bagheera", 9000.0, R.drawable.matra_bagheera, 1973, "0320335579","test9@gmail.com", "À vendre : Matra Bagheera de 1973, une voiture de sport unique des années 70. Dotée d'une configuration à trois places en rangée, cette Matra offre une expérience de conduite distinctive. Avec un kilométrage de 70 000 km, elle a été bien entretenue et présente un intérieur soigné. La carrosserie est en bon état, avec quelques marques d'usure normales pour son âge. Prix compétitif et ouvert à la négociation. Une opportunité idéale pour les amateurs de voitures françaises originales."));
        listedAnnonces.add(new AdModel("Citroën CX", 16990.0, R.drawable.citroen_cx, 1974, "0320335580","test10@gmail.com", "À vendre : Citroën CX de 1974, une berline élégante et innovante des années 70. Propulsée par le célèbre système hydropneumatique de Citroën, cette CX offre un confort de conduite exceptionnel. Avec un kilométrage de 90 000 km, elle a été méticuleusement entretenue et présente un intérieur spacieux en bon état. La carrosserie arbore une peinture d'origine bien préservée. Prix attractif et possibilité de négociation. Une occasion rare de posséder une voiture française classique et confortable."));
        listedAnnonces.add(new AdModel("Lamborghini Countach", 597823.0, R.drawable.lambo_countach, 1974, "0320335581","test11@gmail.com", "À vendre : Lamborghini Countach de 1974, une supercar emblématique des années 70. Propulsée par un moteur V12 atmosphérique, cette Countach offre des performances stupéfiantes. Avec seulement 25 000 km au compteur, elle est en excellent état, tant à l'intérieur qu'à l'extérieur. L'intérieur est luxueux avec des finitions en cuir de haute qualité. Prix sur demande pour les collectionneurs sérieux. Une opportunité unique de posséder un morceau de l'histoire de l'automobile italienne."));
        listedAnnonces.add(new AdModel("Volkswagen Golf", 23990.0, R.drawable.vw_golf, 1974, "0320335582","test12@gmail.com", "À vendre : Volkswagen Golf de 1974, une voiture compacte iconique des années 70. Propulsée par un moteur robuste et économique, cette Golf offre une conduite agréable et fiable. Avec un kilométrage de 80 000 km, elle a été bien entretenue et présente un intérieur propre et confortable. La carrosserie est en bon état, avec quelques signes d'usure normale pour son âge. Prix abordable et possibilité de négociation. Idéale pour les amateurs de voitures classiques et pratiques."));
        listedAnnonces.add(new AdModel("Jaguar XJS", 32000.0, R.drawable.jaguar_xjs, 1975, "0320335583","test13@gmail.com", "À vendre : Jaguar XJS de 1975, une voiture de grand tourisme élégante et puissante des années 70. Propulsée par un moteur 12 cylindres, cette XJS offre une conduite luxueuse et raffinée. Avec un kilométrage de 60 000 km, elle a été bien entretenue et présente un intérieur spacieux en cuir de haute qualité. La carrosserie est en bon état, avec une peinture brillante d'origine. Prix compétitif et possibilité de négociation. Une occasion idéale pour les amateurs de voitures classiques britanniques."));
        listedAnnonces.add(new AdModel("Ferrari 308 GTB", 160977.0, R.drawable.ferrari_308_gtb, 1975, "0320335584","test14@gmail.com", "À vendre : Ferrari 308 GTB de 1975, une véritable icône des voitures de sport des années 70. Dotée d'un moteur V8 en position centrale, cette Ferrari offre des performances exceptionnelles et un son envoûtant. Avec un kilométrage de 40 000 km, elle est en excellent état, tant à l'intérieur qu'à l'extérieur. L'intérieur luxueux est agrémenté de finitions en cuir de haute qualité. Prix sur demande pour les collectionneurs sérieux. Une occasion rare de posséder un morceau de l'histoire de l'automobile italienne."));
        listedAnnonces.add(new AdModel("Peugeot 604", 7500.0, R.drawable.peugeot_604, 1975, "0320335585","test15@gmail.com", "À vendre : Peugeot 604 de 1975, une berline haut de gamme française des années 70. Propulsée par un moteur robuste et fiable, cette Peugeot offre un confort de conduite exceptionnel. Avec un kilométrage de 70 000 km, elle a été bien entretenue et présente un intérieur spacieux et luxueux en velours. La carrosserie est en bon état, avec une peinture d'origine bien conservée. Prix attractif et possibilité de négociation. Une voiture de collection idéale pour les amateurs de voitures françaises classiques."));
        listedAnnonces.add(new AdModel("Renault 30", 4990.0, R.drawable.renault_30, 1975, "0320335586","test16@gmail.com", "À vendre : Renault 30 de 1975, une berline élégante et innovante des années 70. Propulsée par un moteur V6, cette Renault offre une conduite confortable et silencieuse. Avec un kilométrage de 80 000 km, elle a été méticuleusement entretenue et présente un intérieur spacieux en tissu en excellent état. La carrosserie est bien préservée, avec une peinture d'origine brillante. Prix abordable et possibilité de négociation. Une opportunité idéale pour les amateurs de voitures françaises rétro."));
        listedAnnonces.add(new AdModel("Simca 1307", 16151.0, R.drawable.simca_1307, 1975, "0320335587","test17@gmail.com", "À vendre : Simca 1307 de 1975, une voiture familiale polyvalente des années 70. Propulsée par un moteur robuste et économique, cette Simca offre une conduite confortable et fiable. Avec un kilométrage de 90 000 km, elle a été bien entretenue et présente un intérieur spacieux et fonctionnel en tissu. La carrosserie est en bon état, avec quelques signes d'usure normale pour son âge. Prix abordable et possibilité de négociation. Idéale pour les amateurs de voitures classiques françaises."));
        listedAnnonces.add(new AdModel("Triumph TR7", 7260.0, R.drawable.triumph_tr7, 1975, "0320335588","test18@gmail.com", "À vendre : Triumph TR7 de 1975, une voiture de sport emblématique des années 70. Dotée d'un design distinctif et de lignes épurées, cette TR7 offre une conduite dynamique et plaisante. Avec un kilométrage de 70 000 km, elle a été bien entretenue et présente un intérieur sportif en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une occasion idéale pour les passionnés de voitures classiques britanniques."));
        listedAnnonces.add(new AdModel("Porsche 924", 11900.0, R.drawable.porsche_924, 1976, "0320335589","test19@gmail.com", "À vendre : Porsche 924 de 1976, une voiture de sport emblématique des années 70. Propulsée par un moteur quatre cylindres en ligne, cette Porsche offre une conduite agile et sportive. Avec un kilométrage de 80 000 km, elle a été bien entretenue et présente un intérieur spacieux en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une opportunité idéale pour les amateurs de voitures classiques allemandes."));
        listedAnnonces.add(new AdModel("Fiat 131 Abarth", 70273.0, R.drawable.fiat_131_abarth, 1976, "0320335590","test20@gmail.com", "À vendre : Fiat 131 Abarth de 1976, une légende du rallye des années 70. Propulsée par un moteur quatre cylindres préparé par Abarth, cette Fiat offre des performances exceptionnelles. Avec un kilométrage de 70 000 km, elle a été méticuleusement entretenue et présente un intérieur sportif en bon état. La carrosserie arbore une livrée emblématique de course, avec des détails authentiques. Prix compétitif pour les connaisseurs passionnés. Une opportunité rare de posséder une pièce de l'histoire du sport automobile italien."));
        listedAnnonces.add(new AdModel("Volvo 240", 6890.0, R.drawable.volvo_240, 1976, "0320335591","test21@gmail.com", "À vendre : Volvo 240 de 1976, une berline robuste et fiable des années 70. Propulsée par un moteur quatre cylindres réputé pour sa longévité, cette Volvo offre une conduite confortable et sécuritaire. Avec un kilométrage de 90 000 km, elle a été bien entretenue et présente un intérieur spacieux en tissu en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix abordable et possibilité de négociation. Parfaite pour les amateurs de voitures classiques suédoises."));
        listedAnnonces.add(new AdModel("Audi 100 5C", 12000.0, R.drawable.audi_100, 1977, "0320335592","test22@gmail.com", "À vendre : Audi 100 5C de 1977, une berline élégante et confortable des années 70. Propulsée par un moteur robuste et fiable, cette Audi offre une conduite agréable et sécuritaire. Avec un kilométrage de 80 000 km, elle a été bien entretenue et présente un intérieur spacieux en tissu en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une voiture de collection idéale pour les amateurs de voitures allemandes classiques."));
        listedAnnonces.add(new AdModel("BMW 323i", 119990.0, R.drawable.bmw_323i, 1977, "0320335593","test23@gmail.com", "À vendre : BMW 323i de 1977, une berline sportive et raffinée des années 70. Propulsée par un moteur six cylindres en ligne, cette BMW offre des performances exceptionnelles et une conduite dynamique. Avec un kilométrage de 70 000 km, elle a été méticuleusement entretenue et présente un intérieur luxueux en cuir en excellent état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une occasion idéale pour les passionnés de voitures allemandes classiques."));
        listedAnnonces.add(new AdModel("Porsche 928", 69900.0, R.drawable.porsche_928, 1977, "0320335594","test24@gmail.com", "À vendre : Porsche 928 de 1977, une GT de luxe emblématique des années 70. Propulsée par un moteur V8, cette Porsche offre des performances exceptionnelles et un confort de conduite supérieur. Avec un kilométrage de 80 000 km, elle a été méticuleusement entretenue et présente un intérieur luxueux en cuir en excellent état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif pour les collectionneurs sérieux. Une opportunité rare de posséder une pièce de l'histoire de Porsche."));
    }


    


}


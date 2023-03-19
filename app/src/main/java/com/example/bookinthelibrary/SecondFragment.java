package com.example.bookinthelibrary;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.bookinthelibrary.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);

                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, intent, 0);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity().getApplicationContext());
                String NOTIFICATION_CHANEL_ID = "my_chanel_id_01";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){NotificationChannel notificationChannel = new
                            NotificationChannel(NOTIFICATION_CHANEL_ID, "my notification", NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.setDescription("Description");
                    notificationChannel.setLightColor(Color.GREEN);
                    notificationChannel.enableLights(true);
                    notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), NOTIFICATION_CHANEL_ID)
                            .setContentTitle("Book In The Library")
                            .setContentText("Sua compra foi finalizada com sucesso!")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setContentIntent(pendingIntent);
                            //.setAutoCancel(true);
                    notificationManager.notify(0001, builder.build());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
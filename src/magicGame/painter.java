package magicGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class painter extends Application{ 

	public Parts back, entity;
	public static Projectiles projectiles = new Projectiles();
	public static CheckBoxs hardCB = new CheckBoxs();
	public static CheckBoxs softCB = new CheckBoxs();
	public static Group  pane = new Group ();
	public static Particles particles = new Particles();
	public static Enemys enemys = new Enemys();
	public static 	ConstructPanel cp;
	
	static CheckBoxs fieldCB = new CheckBoxs();
	
	int freshCounter, timecounter;
	Player player;
	Control control;


	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		back = new Parts();
		entity = new Parts();
		initUI(primaryStage);
	}
	
	void initUI(Stage stage) {
		cp = new ConstructPanel(stage);
		
		back.add(0, 0, new Image("/images/backGround.png"), 0, 0);
		back.add(0, 520, new Image("/images/floor.png"), 1280, 200);
		
		Image boardImage = new Image("/images/board1.png");
		for (int i = 0; i < 5; i ++) {
			back.add(i * 50 + 240, 345, boardImage, 50, 25);
		}
		for (int i = 0; i < 5; i ++) {
			back.add(1280 - (i * 50 + 300), 355, boardImage, 50, 25);
		}
		
		fieldCB.add(new CheckBox(0,0,1280, 0));
		fieldCB.add(new CheckBox(0,0,0, 720));
		fieldCB.add(new CheckBox(1280,0,0, 720));
		
		for (Part part: back) {
			ImageView iv = new ImageView(part.getImage());
			iv.relocate(part.getX(), part.getY());
			pane.getChildren().add(iv);
		}
		hardCB.add(back);
		particles.add(new MahoJing());
		player = new Player();
		enemys.add(new TrainerMan());
		
		
		
//		ImageView playerView = new ImageView(player.getImage());
//		playerView.relocate(player.getX(), player.getY());
//		pane.getChildren().add(playerView);
//		
//		ImageView staffView = new ImageView("/images/staff.png");
//		staffView.relocate(player.getStaffX(), player.getStaffY());
//		pane.getChildren().add(staffView);
		

		Scene scene = new Scene(pane, 1280, 720);
		control = new ControlFight(this);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			public void handle(KeyEvent event) {
				control.keyPressed(event);
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
					
			public void handle(KeyEvent event) {
				control.keyReleased(event);
			}
		});
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				control.mouseMoved(e);
			}
		});
		
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				control.mouseDragged(e);
			}
		});
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				control.mousePressed(e);
			}
		});

		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				control.mouseReleased(e);
			}
		});
		
		
		
		
		stage.setTitle("magic game");
		stage.setScene(scene);
		stage.show();
		
		freshCounter = 0;
		timecounter = 0;
		
		AnimationTimer timer =new AnimationTimer() {
            @Override
            public void handle(long now) {
            	control.updateTimer();
            }
        };
        timer.start();
        
        
        Timer FPStimer = new Timer();
        TimerTask Task1 = new TimerTask() {
            @Override
            public void run() {
                timecounter += 1;
            }
        };
        FPStimer.scheduleAtFixedRate(Task1, 0l, 1);
        
        
        scene.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				timer.stop();
			}
		});
        
        scene.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				timer.start();
			}
		});

	}
	
	public void showFPS() {
		freshCounter += 1;
		if (timecounter >= 1000) {
			timecounter = 0;
			System.out.println(freshCounter);
			this.freshCounter = 0;
		}
	}

}

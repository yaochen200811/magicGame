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

import javafx.animation.AnimationTimer;

public class painter extends Application{ 

	public Parts back, entity;
	public static Projectiles projectiles = new Projectiles();
	public static CheckBoxs hardCB = new CheckBoxs();
	public static CheckBoxs softCB = new CheckBoxs();
	
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
		
		back.add(0, 0, new Image("/images/backGround.jpg"), 0, 0);
		back.add(0, 520, new Image("/images/floor.jpg"), 1280, 200);
		
		CheckBoxs fieldCB = new CheckBoxs();
		fieldCB.add(new CheckBox(0,0,1280, 0));
		fieldCB.add(new CheckBox(0,0,0, 720));
		fieldCB.add(new CheckBox(1280,0,0, 720));
		
		Player player = new Player();
		
		Group  pane = new Group ();
		
		for (Part part: back) {
			ImageView iv = new ImageView(part.getImage());
			iv.relocate(part.getX(), part.getY());
			pane.getChildren().add(iv);
		}
		
		ImageView playerView = new ImageView(player.getImage());
		playerView.relocate(player.getX(), player.getY());
		pane.getChildren().add(playerView);
		
		ImageView staffView = new ImageView("/images/staff.png");
		staffView.relocate(player.getStaffX(), player.getStaffY());
		pane.getChildren().add(staffView);

		Scene scene = new Scene(pane, 1280, 720);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			
			public void handle(KeyEvent event) {
				System.out.println("key pressed: " + event.getCode());
				switch (event.getCode()) {
				case D:
					player.startMovingRight();
					break;
				case A:
					player.startMovingLeft();
					break;
				case SPACE:
					player.startJumping();
					break;
				default:
					break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
					
			public void handle(KeyEvent event) {
				System.out.println("key released: " + event.getCode());
				switch (event.getCode()) {
				case D:
					player.stopMovingRight();
					break;
				case A:
					player.stopMovingLeft();
					break;
				default:
					break;
				}
			}
		});
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				player.setLookAt(e.getSceneX(), e.getSceneY());
			}
		});
		
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				player.setLookAt(e.getSceneX(), e.getSceneY());
			}
		});
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				player.setFireDown(true);
			}
		});
		
		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				player.setFireDown(false);
			}
		});
		
		
		
		
		stage.setTitle("magic game");
		stage.setScene(scene);
		stage.show();
		
		AnimationTimer timer =new AnimationTimer() {
            @Override
            public void handle(long now) {
            	hardCB.add(back);
            	player.updateState();
            	player.checkMove(new CheckBoxs[]{hardCB, fieldCB});
            	playerView.relocate(player.getX(),player.getY());
            	playerView.setScaleX(player.getFaceDir());
            	staffView.relocate(player.getStaffX(), player.getStaffY());
            	staffView.setRotate(player.getStaffRotation());;
				try {
					pane.getChildren().add(player.repuireFire());
				}catch(Exception ex) {
					
				}
				projectiles.update(new CheckBoxs[]{hardCB, fieldCB}, pane);
            }
        };
        timer.start();
        
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

}

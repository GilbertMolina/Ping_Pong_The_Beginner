package moligamescr.pingpongthebeginner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PingPongView extends View {
    public static int puntaje_obtenido = 0;

    private final float paddle_width = 250f;
    private final float paddle_height = 50f;
    private float touch_x = 0f;

    private float ball_x = 250f;
    private float ball_y = 500f;
    private float ball_x_velocity = MenuPingPong.velocidadDificultadSeleccionada;
    private float ball_y_velocity = MenuPingPong.velocidadDificultadSeleccionada;
    private float ball_radius = 35f;
    private RectF ball = new RectF(ball_x - ball_radius, ball_y - ball_radius, ball_x + ball_radius, ball_y + ball_radius);

    private float player_x = 0f;
    private RectF player_paddle = new RectF(player_x - paddle_width, getHeight() - paddle_height - 100f, player_x + paddle_width, getHeight() - 100f);
    private float opponent_x = 0f;
    private RectF opponent_paddle = new RectF(opponent_x - paddle_width, 100f, opponent_x + paddle_width, paddle_height + 100f);

    private Paint ball_color = new Paint();
    private Paint player_color = new Paint();
    private Paint opponent_color = new Paint();

    public PingPongView(Context context) {
        super(context);
    }

    public PingPongView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public PingPongView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        physics();
        ball_color.setARGB(255, 255, 255, 255);
        player_color.setARGB(255, 255, 0, 0);
        opponent_color.setARGB(255, 30, 144, 255);
        canvas.drawOval(ball, ball_color);
        canvas.drawRect(player_paddle, player_color);
        canvas.drawRect(opponent_paddle, opponent_color);
        invalidate();
    }

    private void physics() {
        ball_x += ball_x_velocity;
        ball_y += ball_y_velocity;
        if ((ball.left >= player_paddle.left) && (ball.right <= player_paddle.right) && (ball_y >= player_paddle.top) && (ball_y > player_paddle.bottom)){
            ball_y = player_paddle.top - ball_radius;
        }
        ball.set(ball_x - ball_radius, ball_y - ball_radius, ball_x + ball_radius, ball_y + ball_radius);
        setPlayerPaddle();
        setOpponentPaddle();
        if (ball_x_velocity > -MenuPingPong.velocidadDificultadSeleccionada + 15 && ball_x_velocity < MenuPingPong.velocidadDificultadSeleccionada + 15){
            ball_x_velocity *= 1.01f;
            ball_y_velocity *= 1.01f;
        }
        getBallVelocity();
    }

    private void setPlayerPaddle() {
        if ((touch_x - paddle_width / 2) < 0){
            player_paddle.set(0, getHeight() - paddle_height - 100f, paddle_width, getHeight() - 100f);
        } else if ((touch_x + paddle_width / 2) > getWidth()){
            player_paddle.set(getWidth() - paddle_width, getHeight() - paddle_height - 100f, getWidth(), getHeight() - 100f);
        } else {
            player_paddle.set(touch_x - paddle_width / 2, getHeight() - paddle_height - 100f, touch_x + paddle_width / 2, getHeight() - 100f);
        }
    }

    private void setOpponentPaddle() {
        if (ball.left + paddle_width >= getWidth()){
            opponent_paddle.set((getWidth() - paddle_width), 100f, getWidth(), paddle_height + 100f);
        } else {
            opponent_paddle.set(ball.left, 100f, ball.left + paddle_width, paddle_height + 100f);
        }
    }

    private void getBallVelocity() {
        if ((ball.left >= player_paddle.left) && (ball.right <= player_paddle.right) && (ball.bottom >= player_paddle.top) && (ball.bottom <= player_paddle.top + ball_y_velocity)){
            ball_x_velocity = (Math.random() > 0.5) ? ball_x_velocity : -ball_x_velocity;
            ball_y_velocity = -ball_y_velocity;
            puntaje_obtenido++;
        }
        if (((ball.left >= opponent_paddle.left) && (ball.right <= opponent_paddle.right)) && (ball.top <= opponent_paddle.bottom)){
            ball_x_velocity = (Math.random() > 0.5) ? ball_x_velocity : -ball_x_velocity;
            ball_y_velocity = Math.abs(ball_y_velocity);
        }
        if (ball.right >= getWidth()){
            ball_x_velocity = -ball_x_velocity;
        }
        if (ball.left <= 0f){
            ball_x_velocity = -ball_x_velocity;
        }
        if (ball.top >= getHeight()){
            ball_y_velocity = -ball_y_velocity;
        }
        if (ball.bottom <= 0f){
            ball_y_velocity = -ball_y_velocity;
        }
        if (ball.top > player_paddle.bottom && (ball.left < player_paddle.left || ball.right > player_paddle.right)){
            ball_x = getWidth() / 2;
            ball_y = getHeight() / 2;
            ball_x_velocity = (Math.random() > 0.5) ? -5f : 5f;
            ball_y_velocity = 10;
            try {
                if (puntaje_obtenido <= 2){
                    puntaje_obtenido = 0;
                } else if (puntaje_obtenido > 2){
                    puntaje_obtenido -= 2;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }
        if (ball.top < opponent_paddle.top){
            ball_x = getWidth() / 2;
            ball_y = getHeight() / 2;
            ball_x_velocity = 5f;
            ball_y_velocity = 10;
        }
        if ((ball.left < player_paddle.left) && (ball.right > player_paddle.left) && (ball.bottom >= player_paddle.top)){
            puntaje_obtenido++;
            ball_x_velocity = -ball_x_velocity;
            ball_y_velocity = -ball_y_velocity;
        }
        if ((ball.right < player_paddle.right) && (ball.left > player_paddle.right) && (ball.bottom >= player_paddle.top)){
            puntaje_obtenido++;
            ball_x_velocity = -ball_x_velocity;
            ball_y_velocity = -ball_y_velocity;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        final MotionEvent t = e;
        final float x = t.getRawX();
        touch_x = x;
        return true;
    }

}

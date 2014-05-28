package org.howardbates.atbat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView strikeText, ballText, foulText, outText, runText, inningText, ourScore, opponentScore;
	private ImageView inningPart;
	private RadioGroup radioSide;
	private DataHandler handleData;
	private boolean inningTop, weAreHome, currentInning, modifiedStrike;
	private final int MAX = 2, NUM_VALUES = 8, STRIKE = 0, BALL = 1, FOUL = 2, OUT = 3, RUN = 4, OURSCORE = 5, OPPONENTSCORE = 6, INNING = 7;
	private int[] screenValues;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.layout_menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
//			Toast.makeText(getApplicationContext(), "Settings selected", Toast.LENGTH_LONG).show();
//			startActivity(new Intent(this, Settings.class));
			return true;
		} else if (id == R.id.action_about) {
//			Toast.makeText(getApplicationContext(), "About selected", Toast.LENGTH_LONG).show();
			startActivity(new Intent(this, AboutActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		handleData = new DataHandler(this.getApplicationContext());
		screenValues = new int[NUM_VALUES];
		strikeText = (TextView)findViewById(R.id.strikeText);
		ballText = (TextView)findViewById(R.id.ballText);
		foulText = (TextView)findViewById(R.id.foulText);
		outText = (TextView)findViewById(R.id.outText);
		runText = (TextView)findViewById(R.id.runText);
		inningText = (TextView)findViewById(R.id.inningText);
		inningPart = (ImageView)findViewById(R.id.inningPart);
		radioSide = (RadioGroup)findViewById(R.id.radioSide);
		ourScore = (TextView)findViewById(R.id.ourScore);
		opponentScore = (TextView)findViewById(R.id.opponentScore);
		inningTop = true;
		currentInning = inningTop;
		setWeAreHome();
		inningPart.setImageResource(R.drawable.uparrow);
		setScreenValues();
		for (int i = 0; i < 2; i++) {
			Toast.makeText(getApplicationContext(), "You MUST select home or visitor!", Toast.LENGTH_LONG).show();
		}
	}

	private void setWeAreHome() {
		radioSide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				if (checkedId == R.id.homeButton) {
					weAreHome = true;
				} else if (checkedId == R.id.visitorButton) {
					weAreHome = false;
				}
			}
		});
	}

	private void setScreenValues() {
		screenValues[STRIKE] = Integer.parseInt(strikeText.getText().toString());
		screenValues[BALL] = Integer.parseInt(ballText.getText().toString());
		screenValues[FOUL] = Integer.parseInt(foulText.getText().toString());
		screenValues[OUT] = Integer.parseInt(outText.getText().toString());
		screenValues[RUN] = Integer.parseInt(runText.getText().toString());
		screenValues[OURSCORE] = Integer.parseInt(ourScore.getText().toString());
		screenValues[OPPONENTSCORE] = Integer.parseInt(opponentScore.getText().toString());
		screenValues[INNING] = Integer.parseInt(inningText.getText().toString());
		currentInning = inningTop;
	}

	private void populateScreenValues() {
		strikeText.setText(Integer.toString(screenValues[STRIKE]));
		ballText.setText(Integer.toString(screenValues[BALL]));
		foulText.setText(Integer.toString(screenValues[FOUL]));
		outText.setText(Integer.toString(screenValues[OUT]));
		runText.setText(Integer.toString(screenValues[RUN]));
		ourScore.setText(Integer.toString(screenValues[OURSCORE]));
		opponentScore.setText(Integer.toString(screenValues[OPPONENTSCORE]));
		inningText.setText(Integer.toString(screenValues[INNING]));
		inningTop = currentInning;
		if (currentInning) {
			inningPart.setImageResource(R.drawable.uparrow);
		} else {
			inningPart.setImageResource(R.drawable.downarrow);
		}
	}

	public void strikeButtonClick(View v) {
		setScreenValues();
		int strikes = Integer.parseInt(strikeText.getText().toString());
		if (strikes < MAX) {
			strikes++;
			strikeText.setText(Integer.toString(strikes));
		} else {
			strikeText.setText(Integer.toString(0));
			foulText.setText(Integer.toString(0));
			ballText.setText(Integer.toString(0));
			int outs = Integer.parseInt(outText.getText().toString());
			if (outs < MAX) {
				outs++;
				outText.setText(Integer.toString(outs));
			} else {
				outText.setText(Integer.toString(0));
                    runText.setText(Integer.toString(0));
				int inning = Integer.parseInt(inningText.getText().toString());
				if (inningTop) {
					inningTop = false;
					inningPart.setImageResource(R.drawable.downarrow);
				} else {
					inningTop = true;
					inningPart.setImageResource(R.drawable.uparrow);
					inning++;
					inningText.setText(Integer.toString(inning));
				}
			}
		}
	}

	public void ballButtonClick(View v) {
		setScreenValues();
		int balls = Integer.parseInt(ballText.getText().toString());
		final int MAX_BALLS = 3;
		if (balls < MAX_BALLS) {
			balls++;
			ballText.setText(Integer.toString(balls));
		} else {
			ballText.setText(Integer.toString(0));
			strikeText.setText(Integer.toString(0));
			foulText.setText(Integer.toString(0));
		}
	}

	public void foulButtonClick(View v) {
		setScreenValues();
		int fouls = Integer.parseInt(foulText.getText().toString());
		int strikes = Integer.parseInt(strikeText.getText().toString());
		fouls++;
		foulText.setText(Integer.toString(fouls));
//		modifiedStrike = false;
		if (strikes < MAX) {
//			modifiedStrike = true;
			strikes++;
			strikeText.setText(Integer.toString(strikes));
		}
	}

	public void outButtonClick(View v) {
		setScreenValues();
		int outs = Integer.parseInt(outText.getText().toString());
		if (outs < MAX) {
			outs++;
			outText.setText(Integer.toString(outs));
			strikeText.setText(Integer.toString(0));
			foulText.setText(Integer.toString(0));
			ballText.setText(Integer.toString(0));
		} else {
			outText.setText(Integer.toString(0));
               ballText.setText(Integer.toString(0));
               strikeText.setText(Integer.toString(0));
               foulText.setText(Integer.toString(0));
               runText.setText(Integer.toString(0));
               int inning = Integer.parseInt(inningText.getText().toString());
               if (inningTop) {
                    inningTop = false;
	               inningPart.setImageResource(R.drawable.downarrow);
               } else {
                    inningTop = true;
                    inningPart.setImageResource(R.drawable.uparrow);
                    inning++;
                    inningText.setText(Integer.toString(inning));
               }
		}
	}

	public void runButtonClick(View v) {
		setScreenValues();
		int runs = Integer.parseInt(runText.getText().toString());
		runs++;
		runText.setText(Integer.toString(runs));
		if (weAreHome && inningTop) {
			int score = Integer.parseInt(opponentScore.getText().toString());
			score++;
			opponentScore.setText(Integer.toString(score));
		} else if (!weAreHome && inningTop) {
			int score = Integer.parseInt(ourScore.getText().toString());
			score++;
			ourScore.setText(Integer.toString(score));
		} else if (weAreHome && !inningTop) {
               int score = Integer.parseInt(ourScore.getText().toString());
               score++;
               ourScore.setText(Integer.toString(score));
          } else {
               int score = Integer.parseInt(opponentScore.getText().toString());
               score++;
               opponentScore.setText(Integer.toString(score));
          }
	}

	public void baseButtonClick(View v) {
		setScreenValues();
		ballText.setText(Integer.toString(0));
		strikeText.setText(Integer.toString(0));
		foulText.setText(Integer.toString(0));
	}

	public void undoButtonClick(View v) {
		populateScreenValues();
	}

	public void subtractStrike(View v) {
		setScreenValues();
		int strikes = Integer.parseInt(strikeText.getText().toString());
		if (strikes > 0) {
			strikes--;
			strikeText.setText(Integer.toString(strikes));
		}
	}

	public void subtractBall(View V) {
		setScreenValues();
		int balls = Integer.parseInt(ballText.getText().toString());
		if (balls > 0) {
			balls--;
			ballText.setText(Integer.toString(balls));
		}
	}

	public void subtractFoul(View V) {
		setScreenValues();
		int fouls = Integer.parseInt(foulText.getText().toString());
		if (fouls > 0) {
			fouls--;
			foulText.setText(Integer.toString(fouls));
		}
	}

	public void subtractOut(View V) {
		setScreenValues();
		int outs = Integer.parseInt(outText.getText().toString());
		if (outs > 0) {
			outs--;
			outText.setText(Integer.toString(outs));
		}
	}

	public void subtractRun(View V) {
		setScreenValues();
		int runs = Integer.parseInt(runText.getText().toString());
		if (runs > 0) {
			runs--;
			runText.setText(Integer.toString(runs));
		}
	}

	public void subtractOurScore(View V) {
		setScreenValues();
		int score = Integer.parseInt(ourScore.getText().toString());
		if (score > 0) {
			score--;
			ourScore.setText(Integer.toString(score));
		}
	}

	public void subtractOpponentScore(View V) {
		setScreenValues();
		int score = Integer.parseInt(ourScore.getText().toString());
		if (score > 0) {
			score--;
			opponentScore.setText(Integer.toString(score));
		}
	}

	public void subtractInning(View V) {
		setScreenValues();
		int inning = Integer.parseInt(inningText.getText().toString());
		if (inning > 1) {
			inning--;
			inningText.setText(Integer.toString(inning));
		}
	}

	public void revertInningTop(View v) {
		if (inningTop) {
			inningTop = false;
			inningPart.setImageResource(R.drawable.downarrow);
		} else {
			inningTop = true;
			inningPart.setImageResource(R.drawable.uparrow);
		}
	}
}

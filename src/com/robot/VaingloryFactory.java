package com.robot;


import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.*;
import java.util.*;
import java.net.*;
import java.io.*;

class Data_roles{
	public double kda;
	public int games; 
	public int wins;
	public int loss;
	public double winRate;
	public double avgKills;
	public double avgDeaths;
	public double avgAssists;
	public  Data_roles(){

	}


}
public class VaingloryFactory
{
	public static String Player_info_detail( String player_name)
	{
		if(player_name == null){
			return "false";
		}
		String player_name_encoded ="";
		try
		{
			player_name_encoded = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		BufferedImage ground_bitmap = Util.get_img( "ground_info_detail.png");
		ImageUtil imgutil = new ImageUtil(ground_bitmap);

		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		String message_to_send = "";
		String message = Util.Curl("https://45.77.188.201/player/"+player_name_encoded+"/stats?gameMode=&season=");
		if (message.equals("{}"))
		{
			return "false";
		}
		else
		{
			String name ="";
			String region ="";
			double kda = 0;
			int deaths = 0;
			int assists =0;
			int games = 0;
			int wins =0;
			int loss =0;
			double winRate = 0;
			double kp = 0;
			double avgKills = 0;
			int totalKills = 0;
			double avgDeaths = 0;
			int totalDeaths = 0;
			double avgAssists = 0;
			int totalAssists =0;
			double avgCS = 0;
			int totalCS =0;
			Data_roles data_captain = new Data_roles();
			Data_roles data_carry = new Data_roles();
			Data_roles data_jungle = new Data_roles();
			try
			{
				JSONObject data = new JSONObject(message).getJSONObject("stats");
				name = new JSONObject(message).getString("name");
				kda = data.getDouble("kda");
				games = data.getInt("games");
				wins = data.getInt("wins");
				loss = data.getInt("loss");
				winRate = data.getDouble("winRate");
				kp = data.getDouble("kp");
				avgKills = data.getDouble("avgKills");
				totalKills = data.getInt("totalKills");
				avgDeaths = data.getDouble("avgDeaths");
				totalDeaths = data.getInt("totalDeaths");
				avgAssists = data.getDouble("avgAssists");
				totalAssists = data.getInt("totalAssists");
				avgCS = data.getDouble("avgCS");
				totalCS = data.getInt("totalCS");
				JSONObject captain_data = data.getJSONArray("Roles").getJSONObject(0);
				data_captain.avgAssists =captain_data.getDouble("avgAssists");
				data_captain.avgDeaths =captain_data.getDouble("avgDeaths");
				data_captain.avgKills =captain_data.getDouble("avgKills");
				data_captain.games =captain_data.getInt("games");
				data_captain.kda =captain_data.getDouble("kda");
				data_captain.loss =captain_data.getInt("loss");
				data_captain.winRate =captain_data.getDouble("winRate");
				data_captain.wins =captain_data.getInt("wins");
				JSONObject carry_data = data.getJSONArray("Roles").getJSONObject(1);
				data_carry.avgAssists =carry_data.getDouble("avgAssists");
				data_carry.avgDeaths =carry_data.getDouble("avgDeaths");
				data_carry.avgKills =carry_data.getDouble("avgKills");
				data_carry.games =carry_data.getInt("games");
				data_carry.kda =carry_data.getDouble("kda");
				data_carry.loss =carry_data.getInt("loss");
				data_carry.winRate =carry_data.getDouble("winRate");
				data_carry.wins =carry_data.getInt("wins");
				JSONObject jungle_data = data.getJSONArray("Roles").getJSONObject(2);
				data_jungle.avgAssists =jungle_data.getDouble("avgAssists");
				data_jungle.avgDeaths =jungle_data.getDouble("avgDeaths");
				data_jungle.avgKills =jungle_data.getDouble("avgKills");
				data_jungle.games =jungle_data.getInt("games");
				data_jungle.kda =jungle_data.getDouble("kda");
				data_jungle.loss =jungle_data.getInt("loss");
				data_jungle.winRate =jungle_data.getDouble("winRate");
				data_jungle.wins =jungle_data.getInt("wins");
				float winRate_width = (float) winRate/100*750;
				imgutil.drawLine(40.0f,new Color(139,0,0),250, 315, 250+winRate_width, 315);
				imgutil.drawTextToLeftTop(String.valueOf(winRate)+"%",40,new Color(139,0,0),250+winRate_width+20,290);
				float kp_width = (float) kp/100*750;
				imgutil.drawLine(40.0f,new Color(139,0,0),250, 390, 250+kp_width, 390);
				imgutil.drawTextToLeftTop(String.valueOf(kp)+"%",40,new Color(139,0,0),250+kp_width+20,360);
				imgutil.drawTextToLeftTop(String.valueOf(games)+"总场  "+String.valueOf(wins)+"胜 -- "+String.valueOf(loss)+"败",60,new Color(139,0,0),60,195);
				imgutil.drawTextToLeftTop(String.valueOf(kda),40,new Color(139,0,0),90,690);
				imgutil.drawTextToLeftTop(String.valueOf(avgKills)+"/"+String.valueOf(avgDeaths)+"/"+String.valueOf(avgAssists),30,new Color(139,0,0),45,750);
				imgutil.drawTextToLeftTop(String.valueOf(avgCS),40,new Color(139,0,0),330,690);
				imgutil.drawTextToLeftTop(String.valueOf(totalCS)+"总刀",25,new Color(139,0,0),330,750);
				imgutil.drawTextToLeftTop(String.valueOf(totalKills),40,new Color(139,0,0),600,690);
				imgutil.drawTextToLeftTop(String.valueOf(totalDeaths),40,new Color(139,0,0),855,690);
				imgutil.drawTextToLeftTop(String.valueOf(data_captain.kda),45,new Color(139,0,0),435,1058);
				imgutil.drawTextToLeftTop(String.valueOf(data_captain.avgKills),45,new Color(139,0,0),370,1170);
				imgutil.drawTextToLeftTop(String.valueOf(data_captain.avgDeaths),45,new Color(139,0,0),485,1170);
				imgutil.drawTextToLeftTop(String.valueOf(data_captain.avgAssists),45,new Color(139,0,0),600,1170);
				imgutil.drawTextToLeftTop(String.valueOf(data_captain.winRate)+"%",60,new Color(139,0,0),825,1095);
				imgutil.drawTextToLeftTop(String.valueOf(data_captain.games),45,new Color(139,0,0),870,1155);

				imgutil.drawTextToLeftTop(String.valueOf(data_carry.kda),45,new Color(139,0,0),435,1332);
				imgutil.drawTextToLeftTop(String.valueOf(data_carry.avgKills),45,new Color(139,0,0),370,1444);
				imgutil.drawTextToLeftTop(String.valueOf(data_carry.avgDeaths),45,new Color(139,0,0),485,1444);
				imgutil.drawTextToLeftTop(String.valueOf(data_carry.avgAssists),45,new Color(139,0,0),600,1444);
				imgutil.drawTextToLeftTop(String.valueOf(data_carry.winRate)+"%",60,new Color(139,0,0),825,1369);
				imgutil.drawTextToLeftTop(String.valueOf(data_carry.games),45,new Color(139,0,0),870,1429);

				imgutil.drawTextToLeftTop(String.valueOf(data_jungle.kda),45,new Color(139,0,0),435,1607);
				imgutil.drawTextToLeftTop(String.valueOf(data_jungle.avgKills),45,new Color(139,0,0),370,1719);
				imgutil.drawTextToLeftTop(String.valueOf(data_jungle.avgDeaths),45,new Color(139,0,0),485,1719);
				imgutil.drawTextToLeftTop(String.valueOf(data_jungle.avgAssists),45,new Color(139,0,0),600,1719);
				imgutil.drawTextToLeftTop(String.valueOf(data_jungle.winRate)+"%",60,new Color(139,0,0),825,1644);
				imgutil.drawTextToLeftTop(String.valueOf(data_jungle.games),45,new Color(139,0,0),870,1740);


			}
			catch (JSONException e)
			{
				System.out.println(e.toString());
			}

		}

		BufferedImage createdimg = imgutil.get_img();
		String file_path = Util.save_img(createdimg);
		return file_path;
	}
	
	
	public static String Match_super_detail_img( String player_name, String page)
	{
		if(player_name == null){
			return "false";
		}
		try
		{
			player_name = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		BufferedImage ground_bitmap = Util.get_img("ground_5v5.png");
		ImageUtil imgutil = new ImageUtil(ground_bitmap);

		boolean ground_3v3 = false;


		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		String message = Util.Curl("https://45.77.188.201/matches/" + player_name + "?gameMode=&limit=1&page=" + page + "&season=");
		if (message.equals("[]"))
		{
			return "false";
		}
		else
		{
			int kills = 0;
			int deaths = 0;
			int assists =0;
			double kda=0;
			float gold =0;
			int Ranke_type =0;
			int minionkills = 0;
			String game_id = "";
			int game_type = 1;
			int rankescore = 0;
			String winners_xml = "";
			String losers_xml = "";
			String gamemode ="";
			String region = "";
			String createdat = "";
			String duration = "";
			JSONArray json = null;
			JSONArray lefts_list =null;
			JSONArray rights_list = null;
			JSONArray player_list=null;
			boolean team_winner = false;

			//String tier = null

			DecimalFormat df = new DecimalFormat("0.0");		
			StringBuilder left_list = new StringBuilder("");
			StringBuilder right_list = new StringBuilder("");
			try
			{
				json = new JSONArray(message);
				JSONObject match = json.getJSONObject(0);
		
				gamemode = match.getString("gameMode").replace(" ", "_");
				Ranke_type = Util.Get_ranke_type(gamemode);
				gamemode = Util.match_translate(gamemode);
				region = match.getString("shardId");
				String createdAt = match.getString("createdAt").replaceAll("[A-Z]", " ");
				player_list = match.getJSONArray("players");
				game_id = match.getString("id");
				String telemetry = Util.Curl("https://api.vgpro.gg/matches/"+game_id+"/"+region+"/telemetry");
				JSONObject telemetry_json = new JSONObject(telemetry);
				JSONObject blue_telemetry_list = telemetry_json.getJSONObject("facts").getJSONObject("blue");
				JSONObject red_telemetry_list = telemetry_json.getJSONObject("facts").getJSONObject("red");
				Iterator iterator = blue_telemetry_list.keys();
				long blue_damage = 0;
				long blue_healed = 0;
				long blue_taken = 0;
				while(iterator.hasNext()){
					String key = (String) iterator.next();
					if (!key.equals("KindredSocialPingsManifest")){
						blue_damage = blue_damage+blue_telemetry_list.getJSONObject(key).getLong("damage");
						blue_healed = blue_healed+blue_telemetry_list.getJSONObject(key).getLong("healed");
						blue_taken = blue_taken+blue_telemetry_list.getJSONObject(key).getLong("taken");
					}
				}
				Iterator iterator1 = red_telemetry_list.keys();
				long red_damage = 0;
				long red_healed = 0;
				long red_taken = 0;
				while(iterator1.hasNext()){
					String key = (String) iterator1.next();
					if (!key.equals("KindredSocialPingsManifest")){
						red_damage = red_damage+red_telemetry_list.getJSONObject(key).getLong("damage");
						red_healed = red_healed+red_telemetry_list.getJSONObject(key).getLong("healed");
						red_taken = red_taken+red_telemetry_list.getJSONObject(key).getLong("taken");
					}
				}
				try
				{
					SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date st = lsdStrFormat.parse(createdAt);
					long datelong = st.getTime();
					datelong = datelong + 28800000;
					createdat = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date(datelong)).replaceAll("20[0-9]*-", "");

				}
				catch (ParseException e)
				{}
				if (player_list.length() <= 6 ){
					BufferedImage bitmap = Util.get_img( "ground_3v3.png");
					imgutil.update_img(bitmap);
					ground_3v3=true;
				}
				duration = String.valueOf((int)match.getInt("duration") / 60);
				imgutil.drawTextToLeftBottom( "日期 " + createdat, 40, new Color(193, 210, 240), 0, 10);
				imgutil.drawTextToLeftBottom(duration + "分钟" , 40,new Color(193, 210, 240), 360, 10);
				imgutil.drawTextToLeftBottom(gamemode, 40,new Color(193, 210, 240), 0, 60);
				JSONArray rosters_list = match.getJSONArray("rosters");




				for (int time =0 ; time < player_list.length(); time++)
				{
					JSONObject player=player_list.getJSONObject(time);
					if (player.getString("side").equals("left/blue"))
					{
						left_list.append(player + ",");
					}
					else
					{
						right_list.append(player + ",");
					}
				}
				left_list.append("@@@@");
				right_list.append("@@@@");
				lefts_list = new JSONArray("[" + left_list.toString().replace(",@@@@", "") + "]");
				rights_list = new JSONArray("[" + right_list.toString().replace(",@@@@", "") + "]");
				int data_start_position = 15;
				int struct_start_position = 120;
				int items_start_position = 15;
				int hero_start_position = 35;
				for (int time =0 ; time < lefts_list.length(); time++)
				{
					JSONObject player=lefts_list.getJSONObject(time);
					if (time == 0)
					{
						if (player.getBoolean("winner"))
						{
							team_winner = true;
						}
					}
					Boolean mvp =player.getBoolean("mvp");
					Boolean me =player.getBoolean("me");
					team_winner = player.getBoolean("winner");
					Boolean afk =player.getBoolean("afk");
					String name = player.getString("name");
					name = name.length() > 10 ? name.substring(0, 10) +"..": name;
					String hero = player.getString("hero");
					//hero = Util.hero_translate(hero);
					kills = player.getInt("kills");
					deaths = player.getInt("deaths");
					assists = player.getInt("assists");
					kda = player.getDouble("kda");
					minionkills = player.getInt("minionKills");
					gold = player.getInt("gold");
					gold = Float.parseFloat(df.format((gold / 1000)));
					//tier = String.valueOf(player.getInt("tier"));
					//tier = Util.translate_tier(tier);

					imgutil.drawTextToLeftTop(String.valueOf(minionkills), 30,new Color(193, 210, 240), 720, data_start_position);
					imgutil.drawTextToLeftTop(String.valueOf(gold) + "k", 30,new Color(193, 210, 240), 830, data_start_position);
					imgutil.drawTextToLeftTop(name, 40,new Color(193, 210, 240), 180, data_start_position);
					imgutil.drawTextToLeftTop(String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists), 40,new Color(193, 210, 240), 180, data_start_position+40);
					imgutil.drawTextToLeftTop(String.valueOf(kda), 40,new Color(193, 210, 240), 180, data_start_position+80);
					//Log.d("ggggggg", String.valueOf(data_start_position) + " " + String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists));
					//imgutil.drawTextToLeftTop(String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists), 40, new Color(193, 210, 240), 370, data_start_position);
					JSONArray items_list = player.getJSONArray("items");
					if (items_list.length() != 0)
					{
						int item_start_position = 430;
						int real_items_start_position =items_start_position;
						for (int time_1 = 0; time_1 < items_list.length();)
						{

							if (time_1 == 3){
								item_start_position = 430;
								real_items_start_position =items_start_position +80;


							}
							BufferedImage item_bitmap = Util.get_img( items_list.getString(time_1).replace(" ", "").replace("'", "").toLowerCase()+".png");

							imgutil.createWaterMaskLeftTop(item_bitmap, item_start_position, real_items_start_position);
							item_start_position = item_start_position + 80 ;
							time_1=time_1+1;
						}
					}
					BufferedImage hero_bitmap = Util.get_img( hero.toLowerCase()+".png");
					imgutil.createWaterMaskLeftTop(hero_bitmap, 35, hero_start_position);
					float hero_damage_width = (float)blue_telemetry_list.getJSONObject(hero).getLong("damage") / (float)blue_damage *200*(player_list.length()/2);
					if (hero_damage_width > 200){
						hero_damage_width = 200;
					}
					float hero_healed_width = (float)blue_telemetry_list.getJSONObject(hero).getLong("healed") / (float)blue_healed *200*(player_list.length()/2);
					if (hero_healed_width > 200){
						hero_healed_width = 200;
					}
					float hero_taken_width = (float)blue_telemetry_list.getJSONObject(hero).getLong("taken") / (float)blue_taken *200*(player_list.length()/2);
					if (hero_taken_width > 200){
						hero_taken_width = 200;
					}
					imgutil.drawLine(30.0f,new Color(193, 210, 240),690, data_start_position+50, 690+hero_damage_width, data_start_position+50);
					imgutil.drawTextToLeftTop(String.valueOf(blue_telemetry_list.getJSONObject(hero).getLong("damage")/1000)+"k", 35,new Color(193, 210, 240),690+hero_damage_width+20, data_start_position+25);
					imgutil.drawLine(30.0f,new Color(193, 210, 240),690, data_start_position+100, 690+hero_healed_width, data_start_position+100);
					imgutil.drawTextToLeftTop(String.valueOf(blue_telemetry_list.getJSONObject(hero).getLong("healed")/1000)+"k", 35,new Color(193, 210, 240),690+hero_healed_width+20, data_start_position+75);
					imgutil.drawLine(30.0f,new Color(193, 210, 240),690, data_start_position+150, 690+hero_taken_width, data_start_position+150);
					imgutil.drawTextToLeftTop(String.valueOf(blue_telemetry_list.getJSONObject(hero).getLong("taken")/1000)+"k", 35,new Color(193, 210, 240),690+hero_taken_width+20, data_start_position+125);
					if (Ranke_type != 4){
						if (Ranke_type == 1){
							rankescore= player.getInt("rankvst");
						}else if (Ranke_type == 2){
							rankescore= player.getInt("rank5v5vst");
						}else if (Ranke_type == 3){
							rankescore= player.getInt("blitzvst");
						}
						imgutil.drawTextToLeftTop(String.valueOf(rankescore), 40, new Color(193, 210, 240), 970, data_start_position+120);
						String tier = Util.Match_SkyTier(rankescore).split(" ")[0];
						BufferedImage tier_bitmap = Util.get_img("mini_tier"+tier+".png");
						imgutil.createWaterMaskLeftTop(tier_bitmap, 960, data_start_position);
					}
					data_start_position = data_start_position + 175;
					struct_start_position = struct_start_position + 167;
					items_start_position = items_start_position + 175;
					hero_start_position = hero_start_position + 175;

				}
				JSONObject data = rosters_list.getJSONObject(0);
				String team_gold = String.valueOf(data.getInt("gold") / 1000);
				String team_kill = String.valueOf(data.getInt("heroKills"));
				String team_turret =String.valueOf( data.getInt("turretKills"));
				if (team_winner){
					imgutil.drawTextToLeftTop("胜利", 60, new Color(193, 210, 240), 300, data_start_position+20);
				}else{
					imgutil.drawTextToLeftTop("失败", 60,new Color(193, 210, 240), 300, data_start_position+20);
				}
				imgutil.drawTextToLeftTop(team_kill, 60,new Color(193, 210, 240), 960, data_start_position+20);
				imgutil.drawTextToLeftTop( team_turret, 40,new Color(193, 210, 240), 740, data_start_position+70);
				imgutil.drawTextToLeftTop(team_gold+"K", 40,new Color(193, 210, 240), 760, data_start_position+15);


				int left_start;
				if (ground_3v3){
					left_start= 675;
				}else{
					left_start= 1024;
				}
				data_start_position = 15+left_start;
				struct_start_position = 120+left_start;
				items_start_position = 15 +left_start;
				hero_start_position = 35 +left_start;
				//右边玩家开始处理
				for (int time =0 ; time < rights_list.length(); time++)
				{
					JSONObject player=rights_list.getJSONObject(time);
					if (time == 0)
					{
						if (player.getBoolean("winner"))
						{
							//	imgutil.drawTextToLeftTop(context, ground_bitmap, "胜利方", 40, new Color(193, 210, 240), 0, 45);
						}
						else
						{
							//imgutil.drawTextToLeftTop("失败方", 40, new Color(193, 210, 240), 0, 45);	
						}
					}
					Boolean mvp =player.getBoolean("mvp");
					Boolean me =player.getBoolean("me");
					team_winner = player.getBoolean("winner");
					Boolean afk =player.getBoolean("afk");
					String name = player.getString("name");
					name = name.length() > 10 ? name.substring(0, 10) +"..": name;
					String hero = player.getString("hero");
					//hero = Util.hero_translate(hero);
					kills = player.getInt("kills");
					deaths = player.getInt("deaths");
					assists = player.getInt("assists");
					kda = player.getDouble("kda");
					minionkills = player.getInt("minionKills");
					gold = player.getInt("gold");
					gold = Float.parseFloat(df.format((gold / 1000)));
					//tier = String.valueOf(player.getInt("tier"));
					//tier = Util.translate_tier(tier);
					if (me)
					{
						//imgutil.createWaterMaskLeftTop(context, ground_bitmap, right_player_me_bitmap, 0, struct_start_position);
					}
					//else if (afk == false)
					//{

					//}
					//else if (mvp)
					//{

					//}
					else
					{

						//imgutil.createWaterMaskLeftTop(right_player_bitmap, 0, struct_start_position);

					}
					imgutil.drawTextToLeftTop(String.valueOf(minionkills), 30,new Color(193, 210, 240), 720, data_start_position);
					imgutil.drawTextToLeftTop(String.valueOf(gold) + "k", 30,new Color(193, 210, 240), 830, data_start_position);
					imgutil.drawTextToLeftTop(name, 40,new Color(193, 210, 240), 180, data_start_position);
					imgutil.drawTextToLeftTop(String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists), 40,new Color(193, 210, 240), 180, data_start_position+40);
					imgutil.drawTextToLeftTop(String.valueOf(kda), 40,new Color(193, 210, 240), 180, data_start_position+80);
					JSONArray items_list = player.getJSONArray("items");
					if (items_list.length() != 0)
					{
						int item_start_position = 430;
						int real_items_start_position =items_start_position;
						for (int time_1 = 0; time_1 < items_list.length(); time_1++)
						{
							if (time_1 == 3){
								item_start_position = 430;
								real_items_start_position = items_start_position +80;
							}
							BufferedImage item_bitmap = Util.get_img(items_list.getString(time_1).replace(" ", "").replace("'", "").toLowerCase()+".png");

							imgutil.createWaterMaskLeftTop(item_bitmap, item_start_position, real_items_start_position);
							item_start_position = item_start_position + 80 ;
						}
					}
					BufferedImage hero_bitmap = Util.get_img(hero.toLowerCase()+".png");
					imgutil.createWaterMaskLeftTop(hero_bitmap, 35, hero_start_position);
					float hero_damage_width = (float)red_telemetry_list.getJSONObject(hero).getLong("damage") / (float)red_damage *200*(player_list.length()/2);
					if (hero_damage_width > 200){
						hero_damage_width = 200;
					}
					float hero_healed_width = (float)red_telemetry_list.getJSONObject(hero).getLong("healed") / (float)red_healed *200*(player_list.length()/2);
					if (hero_healed_width > 200){
						hero_healed_width = 200;
					}
					float hero_taken_width = (float)red_telemetry_list.getJSONObject(hero).getLong("taken") / (float)red_taken *200*(player_list.length()/2);
					if (hero_taken_width > 200){
						hero_taken_width = 200;
					}
					imgutil.drawLine(30.0f,new Color(193, 210, 240),690, data_start_position+50, 690+hero_damage_width, data_start_position+50);
					imgutil.drawTextToLeftTop(String.valueOf(red_telemetry_list.getJSONObject(hero).getLong("damage")/1000)+"k", 35,new Color(193, 210, 240),690+hero_damage_width+20, data_start_position+25);
					imgutil.drawLine(30.0f,new Color(193, 210, 240),690, data_start_position+100, 690+hero_healed_width, data_start_position+100);
					imgutil.drawTextToLeftTop(String.valueOf(red_telemetry_list.getJSONObject(hero).getLong("healed")/1000)+"k", 35,new Color(193, 210, 240),690+hero_healed_width+20, data_start_position+75);
					imgutil.drawLine(30.0f,new Color(193, 210, 240),690, data_start_position+150, 690+hero_taken_width, data_start_position+150);
					imgutil.drawTextToLeftTop(String.valueOf(red_telemetry_list.getJSONObject(hero).getLong("taken")/1000)+"k", 35,new Color(193, 210, 240),690+hero_taken_width+20, data_start_position+125);
					if (Ranke_type != 4){
						if (Ranke_type == 1){
							rankescore= player.getInt("rankvst");
						}else if (Ranke_type == 2){
							rankescore= player.getInt("rank5v5vst");
						}else if (Ranke_type == 3){
							rankescore= player.getInt("blitzvst");
						}
						imgutil.drawTextToLeftTop(String.valueOf(rankescore), 40,new Color(193, 210, 240), 970, data_start_position+120);
						String tier = Util.Match_SkyTier(rankescore).split(" ")[0];
						BufferedImage tier_bitmap = Util.get_img("mini_tier"+tier+".png");
						imgutil.createWaterMaskLeftTop(tier_bitmap, 960, data_start_position);

					}

					data_start_position = data_start_position + 175;
					struct_start_position = struct_start_position + 167;
					items_start_position = items_start_position + 175;
					hero_start_position = hero_start_position + 175;

				}

				data = rosters_list.getJSONObject(1);
				team_gold = String.valueOf(data.getInt("gold") / 1000);
				team_kill = String.valueOf(data.getInt("heroKills"));
				team_turret = String.valueOf(data.getInt("turretKills"));
				if (team_winner){
					imgutil.drawTextToLeftTop("胜利", 60,new Color(193, 210, 240), 300, data_start_position+20);
				}else{
					imgutil.drawTextToLeftTop("失败", 60,new Color(193, 210, 240), 300, data_start_position+20);
				}
				imgutil.drawTextToLeftTop(team_kill, 60,new Color(193, 210, 240), 960, data_start_position+20);
				imgutil.drawTextToLeftTop( team_turret, 40,new Color(193, 210, 240), 740, data_start_position+70);
				imgutil.drawTextToLeftTop(team_gold+"K", 40,new Color(193, 210, 240), 760, data_start_position+15);

			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}



		}

		BufferedImage createdimg =imgutil.get_img();
		String file_path = Util.save_img(createdimg);
		return file_path;
    }
	
	public static String Match_history_img( String player_name, String page)
	{
		if(player_name == null){
			return "false";
		}
		try
		{
			player_name = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		BufferedImage ground_bitmap = Util.get_img( "ground_history.png");
    	BufferedImage win_bitmap = Util.get_img( "win.png");
		BufferedImage loss_bitmap = Util.get_img("loss.png");
		BufferedImage title_bitmap = Util.get_img("title.png");
		ImageUtil imgutil = new ImageUtil(ground_bitmap);
		imgutil.createWaterMaskLeftTop(title_bitmap, 0, 10);

		String game_mode = "";

		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		String message = Util.Curl("https://45.77.188.201/matches/" + player_name + "?gameMode=" + game_mode + "&limit=5&page=" + page + "&season=");
		if (message.equals("[]"))
		{
			return "false";
		}
		else
		{
			int kills = 0;
			int deaths = 0;
			int assists =0;
			//int kda=0;
			float gold =0;
			int minionkills = 0;
			int game_type = 1;
			String players = null;
			String winners_xml = "";
			String losers_xml = "";
			String gamemode =null;
			String player_region = null;
			String createdate = null;
			String duration = null;
			String hero = null;
			boolean winner = false;
			JSONArray json = null;
			JSONArray lefts_list =null;
			JSONArray rights_list = null;
			JSONArray player_list=null;
			JSONArray items_list = null;
			//String tier = null

			DecimalFormat df = new DecimalFormat("0.0");		
			StringBuilder left_list = new StringBuilder("");
			StringBuilder right_list = new StringBuilder("");
			try
			{

				json = new JSONArray(message);
				int data_start_position = 130;
				int struct_start_position = 125;
				int items_start_position = 200;
				int hero_start_position = 145;
				int date_start_position = 180;
				for (int time = 0;time < 5;time++)
				{

					JSONObject match = json.getJSONObject(time);
					player_list = match.getJSONArray("players");
					gamemode = match.getString("gameMode").replace(" ", "_");
					gamemode = Util.match_translate(gamemode);
					player_region = match.getString("shardId");
					duration = String.valueOf((int)match.getInt("duration") / 60);

					String createdAt = match.getString("createdAt").replaceAll("[A-Z]", " ");
					try
					{
						SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date st = lsdStrFormat.parse(createdAt);
						long datelong = st.getTime();
						datelong = datelong + 28800000;
						createdate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date(datelong)).replaceAll("20[0-9]*-", "");

					}
					catch (ParseException e)
					{
						System.out.println("Shit"+e.toString());
					}



					for (int time_1 = 0;time_1 < player_list.length();time_1++)
					{

						JSONObject player=player_list.getJSONObject(time_1);
						if (player.getBoolean("me"))
						{

							kills = player.getInt("kills");

							deaths = player.getInt("deaths");
							assists = player.getInt("assists");

							hero = player.getString("hero").toLowerCase();

							winner = player.getBoolean("winner");
							items_list = player.getJSONArray("items");
							minionkills = player.getInt("minionKills");
							gold = player.getInt("gold");
							gold = Float.parseFloat(df.format((gold / 1000)));
							if (winner)
							{
								imgutil.createWaterMaskLeftTop(win_bitmap, 0, struct_start_position);

							}
							else
							{
								
								imgutil.createWaterMaskLeftTop(loss_bitmap, 0, struct_start_position);

							}
							imgutil.drawTextToLeftTop(String.valueOf(minionkills), 40,new Color(193, 210, 240), 730, data_start_position);
							imgutil.drawTextToLeftTop( String.valueOf(gold) + "k", 40,new Color(193, 210, 240), 550, data_start_position);
							imgutil.drawTextToLeftTop(gamemode + " " + duration + "分钟", 40,new Color(193, 210, 240), 0, data_start_position);
							imgutil.drawTextToLeftTop(createdate , 40,new Color(193, 210, 240), 0, date_start_position);


							imgutil.drawTextToLeftTop(String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists), 40,new Color(193, 210, 240), 370, data_start_position);
							if (items_list.length() != 0)
							{
								int item_start_position = 250;
								for (int time_2 = 0; time_2 < items_list.length(); time_2++)
								{
									BufferedImage item_bitmap = Util.get_img(items_list.getString(time_2).replace(" ", "").replace("'", "").toLowerCase()+".png");

									imgutil.createWaterMaskLeftTop(item_bitmap, item_start_position, items_start_position);
									item_start_position = item_start_position + 90 ;
								}
							}
							BufferedImage hero_bitmap = Util.get_img(hero+".png");
							imgutil.createWaterMaskLeftTop(hero_bitmap, 805, hero_start_position);
							imgutil.drawTextToRightBottom("Generated by Gwen QQ_VG_Robot", 40,new Color(193, 210, 240), 5, 60);
							imgutil.drawTextToRightBottom("Powered by Tick Tock & Guild Belove(Her)", 40,new Color(193, 210, 240), 5, 10);
						}




					}

					data_start_position = data_start_position + 167;
					date_start_position = date_start_position + 167;
					struct_start_position = struct_start_position + 167;
					items_start_position = items_start_position + 167;
					hero_start_position = hero_start_position + 167;


				}


			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

		}

		BufferedImage createdimg =imgutil.get_img();
		String file_path = Util.save_img(createdimg);
		return file_path;
	}

	public static String Match_detail_img(String player_name, String page)
	{
		if(player_name == null){
			return "false";
		}
		try
		{
			player_name = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
        BufferedImage ground_bitmap = Util.get_img("ground.png");
    	BufferedImage left_player_bitmap = Util.get_img("left_player.png");
		BufferedImage left_player_me_bitmap = Util.get_img( "left_player_me.png");
		BufferedImage right_player_bitmap = Util.get_img( "right_player.png");
		BufferedImage right_player_me_bitmap = Util.get_img("right_player_me.png");
		ImageUtil imgutil = new ImageUtil(ground_bitmap);
		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}

		String message = Util.Curl("https://45.77.188.201/matches/" + player_name + "?gameMode=&limit=1&page=" + page + "&season=");
		if (message.equals("[]"))
		{
			return "false";
		}
		else
		{
			int kills = 0;
			int deaths = 0;
			int assists =0;
			//int kda=0;
			float gold =0;
			int minionkills = 0;
			int game_type = 1;

			String winners_xml = "";
			String losers_xml = "";
			String gamemode =null;
			String region = null;
			String createdat = null;
			String duration = null;
			JSONArray json = null;
			JSONArray lefts_list =null;
			JSONArray rights_list = null;
			JSONArray player_list=null;
			//String tier = null

			DecimalFormat df = new DecimalFormat("0.0");		
			StringBuilder left_list = new StringBuilder("");
			StringBuilder right_list = new StringBuilder("");
			try
			{
				json = new JSONArray(message);
				JSONObject match = json.getJSONObject(0);
				player_list = match.getJSONArray("players");
				gamemode = match.getString("gameMode").replace(" ", "_");
				gamemode = Util.match_translate(gamemode);
				region = match.getString("shardId");
				String createdAt = match.getString("createdAt").replaceAll("[A-Z]", " ");
				try
				{
					SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date st = lsdStrFormat.parse(createdAt);
					long datelong = st.getTime();
					datelong = datelong + 28800000;
					createdat = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date(datelong)).replaceAll("20[0-9]*-", "");

				}
				catch (ParseException e)
				{}
				duration = String.valueOf((int)match.getInt("duration") / 60);
				imgutil.drawTextToLeftBottom("日期 " + createdat, 40, new Color(193, 210, 240), 0, 10);
				imgutil.drawTextToLeftBottom(duration + "分钟" , 40, new Color(193, 210, 240), 360, 10);
				imgutil.drawTextToLeftBottom(gamemode, 40,new Color(193, 210, 240), 0, 60);
				imgutil.drawTextToRightBottom("Generated by Gwen QQ_VG_Robot", 40,new Color(193, 210, 240), 5, 60);
				imgutil.drawTextToRightBottom("Powered by Tick Tock & Guild Belove(Her)", 40,new Color(193, 210, 240), 5, 10);
				
				JSONArray rosters_list = match.getJSONArray("rosters");
				for (int time = 0; time < rosters_list.length(); time++)
				{
				    JSONObject data = rosters_list.getJSONObject(time);
					String team_gold = String.valueOf(data.getInt("gold") / 1000);
					String team_kill = String.valueOf(data.getInt("heroKills"));
					String team_turret = String.valueOf(data.getInt("turretKills"));
					if (time == 0)
					{
						imgutil.drawTextToLeftTop( team_kill, 60,new Color(193, 210, 240), 860, 45);
						imgutil.drawTextToLeftTop( team_turret, 40,new Color(193, 210, 240), 550, 45);
						imgutil.drawTextToLeftTop(team_gold, 40,new Color(193, 210, 240), 700, 45);
					}
					else
					{
						imgutil.drawTextToRightTop(team_kill, 60,new Color(193, 210, 240), 860, 45);
						imgutil.drawTextToRightTop( team_turret, 40,new Color(193, 210, 240), 550, 45);
						imgutil.drawTextToRightTop(team_gold + "k", 40,new Color(193, 210, 240), 700, 45);

					}
				}



				for (int time =0 ; time < player_list.length(); time++)
				{
					JSONObject player= player_list.getJSONObject(time);
					if (player.getString("side").equals("left/blue"))
					{
						left_list.append(player + ",");
					}
					else
					{
						right_list.append(player + ",");
					}
				}
				left_list.append("@@@@");
				right_list.append("@@@@");
				lefts_list = new JSONArray("[" + left_list.toString().replace(",@@@@", "") + "]");
				rights_list = new JSONArray("[" + right_list.toString().replace(",@@@@", "") + "]");
				if (left_list.length() + right_list.length() == 6)
				{
					game_type = 1;
				}
				else
				{
					game_type = 2;
				}

				if (game_type == 2)
				{


					int data_start_position = 130;
					int struct_start_position = 125;
					int items_start_position = 200;
					int hero_start_position = 145;
					for (int time =0 ; time < lefts_list.length(); time++)
					{
						JSONObject player=lefts_list.getJSONObject(time);
						if (time == 0)
						{
							if (player.getBoolean("winner"))
							{
								imgutil.drawTextToLeftTop("胜利方", 40,new Color(193, 210, 240), 0, 45);
							}
							else
							{
								imgutil.drawTextToLeftTop("失败方", 40,new Color(193, 210, 240), 0, 45);	
							}
						}
						Boolean mvp =player.getBoolean("mvp");
						Boolean me =player.getBoolean("me");
						Boolean afk =player.getBoolean("afk");
						String name = player.getString("name");
						String hero = player.getString("hero").toLowerCase();
						//hero = Util.hero_translate(hero);
						kills = player.getInt("kills");
						deaths = player.getInt("deaths");
						assists = player.getInt("assists");
						//kda = player.getInt("kda");
						minionkills = player.getInt("minionKills");
						gold = player.getInt("gold");
						gold = Float.parseFloat(df.format((gold / 1000)));
						//tier = String.valueOf(player.getInt("tier"));
						//tier = Util.translate_tier(tier);
						if (me)
						{
							imgutil.createWaterMaskLeftTop(left_player_me_bitmap, 0, struct_start_position);
						}
						//else if (afk == false)
						//{

						//}
						//else if (mvp)
						//{

						//}
						else
						{

							imgutil.createWaterMaskLeftTop(left_player_bitmap, 0, struct_start_position);

						}
						imgutil.drawTextToLeftTop( String.valueOf(minionkills), 40,new Color(193, 210, 240), 730, data_start_position);
						imgutil.drawTextToLeftTop(String.valueOf(gold) + "k", 40,new Color(193, 210, 240), 550, data_start_position);
						imgutil.drawTextToLeftTop(name, 40,new Color(193, 210, 240), 0, data_start_position);
						imgutil.drawTextToLeftTop(String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists), 40, new Color(193, 210, 240), 370, data_start_position);
						JSONArray items_list = player.getJSONArray("items");
						if (items_list.length() != 0)
						{
							int item_start_position = 250;
							for (int time_1 = 0; time_1 < items_list.length(); time_1++)
							{
								BufferedImage item_bitmap = Util.get_img( items_list.getString(time_1).replace(" ", "").replace("'", "").toLowerCase()+".png");

								imgutil.createWaterMaskLeftTop(item_bitmap, item_start_position, items_start_position);
								item_start_position = item_start_position + 90 ;
							}
						}
						BufferedImage hero_bitmap = Util.get_img( hero+".png");
						imgutil.createWaterMaskLeftTop(hero_bitmap, 805, hero_start_position);

						data_start_position = data_start_position + 167;
						struct_start_position = struct_start_position + 167;
						items_start_position = items_start_position + 167;
						hero_start_position = hero_start_position + 167;

					}

				    data_start_position = 130;
					struct_start_position = 125;
					items_start_position = 200;
					hero_start_position = 145;
					//右边玩家开始处理
					for (int time =0 ; time < rights_list.length(); time++)
					{
						JSONObject player= rights_list.getJSONObject(time);
						if (time == 0)
						{
							if (player.getBoolean("winner"))
							{
								imgutil.drawTextToRightTop("胜利方", 40,new Color(193, 210, 240), 0, 45);
							}
							else
							{
								imgutil.drawTextToRightTop("失败方", 40,new Color(193, 210, 240), 0, 45);	
							}
						}
						Boolean mvp =player.getBoolean("mvp");
						Boolean me =player.getBoolean("me");
						Boolean afk =player.getBoolean("afk");
						String name = player.getString("name");
						String hero = player.getString("hero").toLowerCase();
						//hero = Util.hero_translate(hero);
						kills = player.getInt("kills");
						deaths = player.getInt("deaths");
						assists = player.getInt("assists");
						//kda = player.getInt("kda");
						minionkills = player.getInt("minionKills");
						gold = player.getInt("gold");
						gold = Float.parseFloat(df.format((gold / 1000)));
						//tier = String.valueOf(player.getInt("tier"));
						//tier = Util.translate_tier(tier);
						if (me)
						{
							imgutil.createWaterMaskRightTop(right_player_me_bitmap, 0, struct_start_position);
						}
						//else if (afk == false)
						//{

						//}
						//else if (mvp)
						//{

						//}
						else
						{

							imgutil.createWaterMaskRightTop(right_player_bitmap, 0, struct_start_position);

						}
						imgutil.drawTextToRightTop( String.valueOf(minionkills), 40,new Color(193, 210, 240), 730, data_start_position);
						imgutil.drawTextToRightTop(String.valueOf(gold) + "k", 40,new Color(193, 210, 240), 550, data_start_position);
						imgutil.drawTextToRightTop(name, 40,new Color(193, 210, 240), 5, data_start_position);
						imgutil.drawTextToRightTop(String.valueOf(kills) + "/" + String.valueOf(deaths) + "/" + String.valueOf(assists), 40,new Color(193, 210, 240), 370, data_start_position);
						JSONArray items_list = player.getJSONArray("items");
						if (items_list.length() != 0)
						{
							int item_start_position = 250;
							for (int time_1 = 0; time_1 < items_list.length(); time_1++)
							{
								BufferedImage item_bitmap = Util.get_img(items_list.getString(time_1).replace(" ", "").replace("'", "").toLowerCase()+".png");

								imgutil.createWaterMaskRightTop(item_bitmap, item_start_position, items_start_position);
								item_start_position = item_start_position + 90 ;
							}
						}
						BufferedImage hero_bitmap = Util.get_img(hero+".png");
						imgutil.createWaterMaskRightTop(hero_bitmap, 805, hero_start_position);

						data_start_position = data_start_position + 167;
						struct_start_position = struct_start_position + 167;
						items_start_position = items_start_position + 167;
						hero_start_position = hero_start_position + 167;

					}


				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

		}
		BufferedImage createdimg =imgutil.get_img();
		String file_path = Util.save_img(createdimg);
		return file_path;
    }
	
	
	
	
	public static String Rank_player(String player_name, String game_mode, String region)
	{
		if(player_name == null){
			return "false";
		}
		try
		{
			player_name = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		String message_to_send="";
		List<List> info_list = new ArrayList<>();
		List<String> info_head = new ArrayList<>();
		info_head.add("ID");
		info_head.add("名次_段位分");
		info_head.add("分段");
		info_list.add(info_head);
		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		String message = Util.Curl("https://45.77.188.201/leaderboard/"+game_mode+"/"+region+"/"+player_name);
		if (message.equals("[]"))
		{
			return "false";
		}
		else
		{
			try
			{
				JSONArray json = new JSONArray(message);
				for (int time = 0 ;time < json.length();time++)
				{
					JSONObject data = json.getJSONObject(time);
					String name = data.getString("name");
					String position = String.valueOf(data.getInt("position"));
					String points = String.valueOf(data.getString("points")).replaceAll("[.].*","");
					String[] color_and_color = Util.Match_SkyTier(Integer.parseInt(points)).split(" ");
					String tier = color_and_color[1];
					String color =color_and_color[2];
					List<String> info = new ArrayList<>();
					info.add(name);
					info.add(position+"_"+points);
					info.add(tier);
					info_list.add(info);
				}
			}
			catch (JSONException e)
			{
			System.out.println(e.toString());
			}
	    		BufferedImage logo_img = Util.get_img("xml_ground123.png");
	    		BufferedImage ground_img = Util.get_random_img("Ranked_Search");
			ImageUtil util = new ImageUtil(ground_img);
			util.drawwater(logo_img,0,0);
			BufferedImage createdimg = util.get_img();
			    createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    String file_path = Util.save_img(createdimg);
			    return file_path;

		}

	}
	public static String Heroes_rank(int sort_mode, String page, String player_region)
	{
		int page_position = 1;
		String hero_name = null;
		int hero_pick_rate_rank = 0;
		int hero_win_rate_rank = 0;
		int hero_ban_rate_rank = 0;
		String hero_pick_rate = null;
		String hero_win_rate = null;
		String hero_ban_rate = null;
		List<List> info_list = new ArrayList<>();
		List<String> info_head = new ArrayList<>();
		info_head.add("英雄");
		info_head.add("胜率");
		info_head.add("扳率");
		info_head.add("选率");


		List<String> info1 = new ArrayList<>();
		List<String> info2 = new ArrayList<>();
		List<String> info3 = new ArrayList<>();
		List<String> info4 = new ArrayList<>();
		List<String> info5 = new ArrayList<>();
		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		String message = Util.Curl("https://45.77.188.201/heroes/" + player_region);
		if (message.equals("[]"))
		{
			return "false";
		}
		else
		{
			try
			{


				JSONArray heroes_list = new JSONArray(message);
				System.out.println(heroes_list.getJSONObject(0));
				int total_page = ((int)Math.ceil((float)heroes_list.length() / 5));
				if (total_page < Integer.parseInt(page))
				{
					return "false";
				}
				for (int time = 1;time < Integer.parseInt(page);time++)
				{
					page_position = page_position + 5;
				}
				for (int time = 1;time <= heroes_list.length();time++)
				{
					JSONObject data = heroes_list.getJSONObject(time - 1);
					if (sort_mode == 1)
					{
						hero_win_rate_rank = data.getJSONObject("rank").getInt("winRate");

						if (page_position <= hero_win_rate_rank && hero_win_rate_rank < page_position + 5)
						{
							if (hero_win_rate_rank == page_position)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info1.add(hero_name);
								info1.add(hero_win_rate);
								info1.add(hero_ban_rate);
								info1.add(hero_pick_rate);
							}
							else if (hero_win_rate_rank - page_position == 1)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info2.add(hero_name);
								info2.add(hero_win_rate);
								info2.add(hero_ban_rate);
								info2.add(hero_pick_rate);
							}
							else if (hero_win_rate_rank - page_position == 2)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info3.add(hero_name);
								info3.add(hero_win_rate);
								info3.add(hero_ban_rate);
								info3.add(hero_pick_rate);
							}
							else if (hero_win_rate_rank - page_position == 3)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info4.add(hero_name);
								info4.add(hero_win_rate);
								info4.add(hero_ban_rate);
								info4.add(hero_pick_rate);
							}
							else if (hero_win_rate_rank - page_position == 4)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info5.add(hero_name);
								info5.add(hero_win_rate);
								info5.add(hero_ban_rate);
								info5.add(hero_pick_rate);
							}
						}
					}
					else if (sort_mode == 2)
					{
						hero_ban_rate_rank = data.getJSONObject("rank").getInt("banRate");
						if (page_position <= hero_ban_rate_rank && hero_ban_rate_rank < page_position + 5)
						{
							if (hero_ban_rate_rank == page_position)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info1.add(hero_name);
								info1.add(hero_win_rate);
								info1.add(hero_ban_rate);
								info1.add(hero_pick_rate);
							}
							else if (hero_ban_rate_rank - page_position == 1)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info2.add(hero_name);
								info2.add(hero_win_rate);
								info2.add(hero_ban_rate);
								info2.add(hero_pick_rate);
							}
							else if (hero_ban_rate_rank - page_position == 2)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info3.add(hero_name);
								info3.add(hero_win_rate);
								info3.add(hero_ban_rate);
								info3.add(hero_pick_rate);
							}
							else if (hero_ban_rate_rank - page_position == 3)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info4.add(hero_name);
								info4.add(hero_win_rate);
								info4.add(hero_ban_rate);
								info4.add(hero_pick_rate);
							}
							else if (hero_ban_rate_rank - page_position == 4)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info5.add(hero_name);
								info5.add(hero_win_rate);
								info5.add(hero_ban_rate);
								info5.add(hero_pick_rate);
							}
						}
					}
					else if (sort_mode == 3)
					{
						hero_pick_rate_rank = data.getJSONObject("rank").getInt("pickRate");
						if (page_position <= hero_pick_rate_rank && hero_pick_rate_rank < page_position + 5)
						{
							if (hero_pick_rate_rank == page_position)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info1.add(hero_name);
								info1.add(hero_win_rate);
								info1.add(hero_ban_rate);
								info1.add(hero_pick_rate);
							}
							else if (hero_pick_rate_rank - page_position == 1)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info2.add(hero_name);
								info2.add(hero_win_rate);
								info2.add(hero_ban_rate);
								info2.add(hero_pick_rate);
							}
							else if (hero_pick_rate_rank - page_position == 2)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info3.add(hero_name);
								info3.add(hero_win_rate);
								info3.add(hero_ban_rate);
								info3.add(hero_pick_rate);
							}
							else if (hero_pick_rate_rank - page_position == 3)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info4.add(hero_name);
								info4.add(hero_win_rate);
								info4.add(hero_ban_rate);
								info4.add(hero_pick_rate);
							}
							else if (hero_pick_rate_rank - page_position == 4)
							{
								hero_name = data.getString("name");
								hero_name = Util.hero_translate(hero_name);
								hero_win_rate = String.valueOf(data.getInt("winRate"));
								hero_ban_rate = String.valueOf(data.getInt("banRate"));
								hero_pick_rate = String.valueOf(data.getInt("pickRate"));
								info5.add(hero_name);
								info5.add(hero_win_rate);
								info5.add(hero_ban_rate);
								info5.add(hero_pick_rate);
							}
						}
					}

				}
				info_list.add(info_head);
				info_list.add(info1);
				info_list.add(info2);
				info_list.add(info3);
				info_list.add(info4);
				info_list.add(info5);



			}
			catch (JSONException e)
			{
				System.out.println( e.toString());
			}
			BufferedImage logo_img = Util.get_img("xml_ground64.png");
			BufferedImage ground_img = Util.get_random_img("Heroes_Ranked");
			ImageUtil util = new ImageUtil(ground_img);
			util.drawwater(logo_img,0,0);
			BufferedImage createdimg = util.get_img();
			createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			String file_path = Util.save_img(createdimg);
		    return file_path;
		}
	}
	
	public static String Heroes_detail(String player_name, String page)
	{
		String player_id="";
		if(player_name == null){
			return "false";
		}
		try
		{
			player_id = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		String message_to_send="";
		int page_position =1;
		List<List> info_list = new ArrayList<>();
		
		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		String message = Util.Curl("https://45.77.188.201/player/" + player_id + "/stats?gameMode=&season=");
		if (message.equals("{}"))
		{
			return "false";
			}
		else
		{
			try
			{
				JSONObject json=new JSONObject(message);
				JSONArray heroes_list = json.getJSONObject("stats").getJSONArray("Heroes");
				String player_region = json.getString("region");
				int total_page = ((int)Math.ceil((float)heroes_list.length() / 5));
				List<String> info_head1 = new ArrayList<>();
				info_head1.add("服务大区: "+player_region+" ID: "+player_name);
				List<String> info_head2 = new ArrayList<>();
				info_head2.add("英雄");
				info_head2.add("K_D_A_KDA");
				info_head2.add("总场_胜场_胜率");
				info_list.add(info_head1);
				info_list.add(info_head2);
				if (total_page < Integer.parseInt(page))
				{
					return "false";
				}
				else
				{
					for (int time = 1;time < Integer.parseInt(page);time++)
					{
						page_position = page_position + 5;
					}
					for (int time = page_position;time <= heroes_list.length() && time < page_position + 5;time++)
					{
						JSONObject data = heroes_list.getJSONObject(time - 1);
						String hero_name = data.getString("name");
						hero_name = Util.hero_translate(hero_name);
						String hero_games = String.valueOf(data.getInt("games"));
						String hero_wins = String.valueOf(data.getInt("wins"));
						String hero_winrate = String.format("%.1f", data.getDouble("winRate"));
						String hero_avgkillsk = String.format("%.1f", data.getDouble("avgKills"));
						String hero_avgdeaths = String.format("%.1f", data.getDouble("avgDeaths"));
						String hero_avgassists = String.format("%.1f", data.getDouble("avgAssists"));
						String hero_kda = String.format("%.1f", data.getDouble("kda"));
						List<String> info = new ArrayList<>();
						info.add(hero_name);
						info.add(hero_avgkillsk + "_" + hero_avgdeaths + "_" + hero_avgassists + "_" + hero_kda);
						info.add(hero_games + "_" + hero_wins + "_" + hero_winrate + "%");
						info_list.add(info);
					
					}

					
					BufferedImage logo_img = Util.get_img("xml_ground73.png");
	    		BufferedImage ground_img = Util.get_random_img("Heroes_Detail");
					ImageUtil util = new ImageUtil(ground_img);
					util.drawwater(logo_img,0,0);
					BufferedImage createdimg = util.get_img();
					createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    String file_path = Util.save_img(createdimg);
					return file_path;

				}

			}
			catch (JSONException e)
			{
				
			    System.out.println( e.toString());
				return "false";
			}
		}
		
	}
	
	
	public static String Game_history(String player_name, String game_mode, String page)
	{
		if(player_name == null){
			return "false";
		}
		String player_id="";
		try
		{
			player_id = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}

		String message = Util.Curl("https://45.77.188.201/matches/" + player_id + "?gameMode=" + game_mode + "&limit=5&page=" + page + "&season=");
		if (message.equals("[]"))
		{
			return "false";
		}
		else
		{
			List<List> info_list = new ArrayList<>();
			
			
			
			int kills = 0;
			int deaths = 0;
			int assists =0;
			int kda=0;
			String hero =null;
			String gamemode =null;
			boolean winner = false;
			String player_region = null;
			String xml = null;
			String createdat = null;
			String message_to_send =null;
			String win_or_lose = "";
			for (int time = 0;time < 5;time++)
			{
				try
				{

					JSONArray json=new JSONArray(message);
					JSONObject match = json.getJSONObject(time);
					JSONArray player_list = match.getJSONArray("players");
					gamemode = match.getString("gameMode").replace(" ", "_");
					gamemode = Util.match_translate(gamemode);
					player_region = match.getString("shardId");
					if(time ==0){
						List<String> info_head_head =new ArrayList<String>();
						info_head_head.add("服务大区: "+player_region+" ID: "+player_name);
						info_list.add(info_head_head);
						List<String> info_head =new ArrayList<String>();
						info_head.add("游戏模式");
						info_head.add("游戏日期");
						info_head.add("K_D_A_KDA");
						info_head.add("英雄");
						info_head.add("胜败");
						info_list.add(info_head);	

					}
				
						String createdAt = match.getString("createdAt").replaceAll("[A-Z]", " ");
						try
						{
							SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date st = lsdStrFormat.parse(createdAt);
							long datelong = st.getTime();
							datelong = datelong + 28800000;
							createdat = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date(datelong)).replaceAll("20[0-9]*-", "");

						}
						catch (ParseException e)
						{
							e.printStackTrace();
						}
					
					for (int time_1 = 0;time_1 < player_list.length();time_1++)
					{
						JSONObject player=player_list.getJSONObject(time_1);
						if (player.getBoolean("me"))
						{
								kills = player.getInt("kills");
								deaths = player.getInt("deaths");
								assists = player.getInt("assists");
								kda = player.getInt("kda");
							hero = player.getString("hero");
							hero = Util.hero_translate(hero);
							winner = player.getBoolean("winner");
						}
					}



				}
				catch (JSONException e)
				{
					System.out.println(e.toString());
				}
				
				
					if (winner == false)
					{
					    win_or_lose = "败";
					}
					else
					{
						win_or_lose = "胜";
					}
					List<String> info =new ArrayList<String>();
					info.add(gamemode);
					info.add(createdat);
					info.add(kills + "/" + deaths + "/" + assists + "_" + kda);
					info.add(hero);
					info.add(win_or_lose);
					info_list.add(info);

			}
			
			BufferedImage logo_img = Util.get_img("xml_ground75.png");
	    		BufferedImage ground_img = Util.get_random_img("Game_History");
			ImageUtil util = new ImageUtil(ground_img);
			util.drawwater(logo_img,0,0);
			BufferedImage createdimg = util.get_img();
			createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    String file_path = Util.save_img(createdimg);
		    return file_path;

		}
	}

  public static String Timer(String player_name)
	{
		String player_id = "";
	  if(player_name == null){
		  return "false";
	  }
	  try
	  {
		  player_id = URLEncoder.encode(player_name, "UTF-8");
	  }
	  catch (UnsupportedEncodingException e)
	  {}
		String message_to_send = "";
		boolean success = false;
		String message =null;
		String aral = "";
		String blitz = "";
		String ranked = "";
		String ranked_5v5 = "";
		String casual = "";
		String casual_5v5 = "";
		String time = "";
		String rank = "暂无数据";
		String rankPercentage = "暂无数据";
	  message = Util.Post("http://vghours.glitch.me/user?ign=" + player_id);
		try
		{
			JSONObject json = new JSONObject(message);
			success = json.getBoolean("success");
			aral = String.valueOf(json.getInt("aral"));
			blitz = String.valueOf(json.getInt("blitz"));
			ranked = String.valueOf(json.getInt("ranked"));
			ranked_5v5 = String.valueOf(json.getInt("ranked_5v5"));
			casual = String.valueOf(json.getInt("casual"));
			casual_5v5 = String.valueOf(json.getInt("casual_5v5"));
			time = String.valueOf(json.getInt("time"));
			rank = String.valueOf(json.getInt("rank"));
			rankPercentage = json.getString("rankPercentage");
			rankPercentage = rankPercentage.split(",")[0].replace("\"", "").replace("[", "").replace(" ", "");
		}
		catch (JSONException e)
		{
			System.out.println(e.toString());
		}
		if (success)
		{
		
			List<List> info_list = new ArrayList<>();
			List<String> info1 =new ArrayList<String>();
			info1.add("游戏ID");
			info1.add(player_name);
			List<String> info2 =new ArrayList<String>();
			info2.add("大乱斗");
			info2.add(aral);
			List<String> info3 =new ArrayList<String>();
			info3.add("闪电战");
			info3.add(blitz);
			List<String> info4 =new ArrayList<String>();
			info4.add("3v3排位");
			info4.add(ranked);
			List<String> info5 =new ArrayList<String>();
			info5.add("5v5排位");
			info5.add(ranked_5v5);
			List<String> info6 =new ArrayList<String>();
			info6.add("3v3匹配");
			info6.add(casual);
			List<String> info7 =new ArrayList<String>();
			info7.add("5v5匹配");
			info7.add(casual_5v5);
			List<String> info8 =new ArrayList<String>();
			info8.add("总游戏时长");
			info8.add(time+"小时");
			List<String> info9 =new ArrayList<String>();
			info9.add("肝硬化排行");
			info9.add(rank);
			List<String> info10 =new ArrayList<String>();
			info10.add("肝硬化金字塔");
			info10.add(rankPercentage);
			info_list.add(info1);
			info_list.add(info2);
			info_list.add(info3);
			info_list.add(info4);
			info_list.add(info5);
			info_list.add(info6);
			info_list.add(info7);
			info_list.add(info8);
			info_list.add(info9);
			info_list.add(info10);
				
			BufferedImage logo_img = Util.get_img("xml_ground102.png");
	    	BufferedImage ground_img = Util.get_random_img("Vainglory_Timer");
		  ImageUtil util = new ImageUtil(ground_img);
		  util.drawwater(logo_img,0,0);
		  BufferedImage createdimg = util.get_img();
		  createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    String file_path = Util.save_img(createdimg);
		    	
			return file_path;
		}
		else
		{
			return "false";
		}

	}
	
	
	public static String Player_info(String player_name, String player_region)
	{
		if(player_name == null){
			return "false";
		}
		String guildTag= null;
		String level = null;
		String xp = null;
		String karmaLevel =null;
		String wins = null;
		String message = null;

		List Controler_list= Util.get_database_contain("robot","Key","key");
		String vg_api_key =Controler_list.toArray()[0].toString();
		if (vg_api_key == "null")
		{
			return "false";
			}
		else
		{
			message = Util.Auth_Curl("https://api.dc01.gamelockerapp.com/shards/" + player_region + "/players?filter[playerNames]=" + player_name, vg_api_key);
			if (message == null)
			{
				return "false";
			}
			else
			{
				try
				{
					JSONObject json = new JSONObject(message);
					JSONObject data = json.getJSONArray("data").getJSONObject(0).getJSONObject("attributes").getJSONObject("stats");
					guildTag = data.getString("guildTag");
					level = String.valueOf(data.getInt("level"));
					xp = String.valueOf(data.getInt("xp"));
					wins = String.valueOf(data.getInt("wins"));
					karmaLevel  = String.valueOf(data.getInt("karmaLevel"));
				}
				catch (JSONException e)
				{
					//return "<msg serviceID=\"33\" brief=\"查询失败\" flag=\"3\" templateID=\"1\"><item bg=\"#00BFFF\" layout=\"4\"><title color=\"#FFFFFF\" size=\"28\">" + e.toString() + "</title></item></msg>";

					System.out.println( e.toString());
				}

				if (karmaLevel.equals("1"))
				{
					karmaLevel = "善";
				}
				else if (karmaLevel.equals("2"))
				{
					karmaLevel = "至善";
				}
				else
				{
					karmaLevel = "恶";
				}
				List<List> info_list = new ArrayList<>();
				List<String> info1 =new ArrayList<String>();
				info1.add("游戏ID");
				info1.add(player_name);
				List<String> info2 =new ArrayList<String>();
				info2.add("服务大区");
				info2.add(player_region);
				List<String> info3 =new ArrayList<String>();
				info3.add("ID等级");
				info3.add(level);
				List<String> info4 =new ArrayList<String>();
				info4.add("总经验值");
				info4.add(xp);
				List<String> info5 =new ArrayList<String>();
				info5.add("总胜场数");
				info5.add(wins);
				List<String> info6 =new ArrayList<String>();
				info6.add("所在公会");
				info6.add(guildTag);
				List<String> info7 =new ArrayList<String>();
				info7.add("业力等级");
				info7.add(karmaLevel);
				info_list.add(info1);
				info_list.add(info2);
				info_list.add(info3);
				info_list.add(info4);
				info_list.add(info5);
				info_list.add(info6);
				info_list.add(info7);
				
				BufferedImage logo_img = Util.get_img("xml_ground72.png");
	    		BufferedImage ground_img = Util.get_random_img("Player_Info");
			    ImageUtil util = new ImageUtil(ground_img);
				util.drawwater(logo_img,0,0);
				BufferedImage createdimg = util.get_img();
			    createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    String file_path = Util.save_img(createdimg);
				
		
				
				return file_path;
			}

			
		   
		}
	}
	public static String Rank_tier(String player_name)
	{
		String player_id ="";
		try
		{
			player_id = URLEncoder.encode(player_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{}
		
		if(player_name == null){
			return "false";
		}
		String message =null;
		String global_rankedranking = null;
		String regional_rankedranking = null;
		String points_rankedranking = null;
		String global_ranked5v5ranking = null;
		String regional_ranked5v5ranking = null;
		String points_ranked5v5ranking = null;
		String global_blitzranking = null;
		String regional_blitzranking = null;
		String points_blitzranking = null;
		String player_region = null;
		String message_to_send = "";
		try
		{
			Util.trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(Util.hv);
		}
		catch (Exception e)
		{}
		message = Util.Curl("https://45.77.188.201/player/" + player_id + "/stats?gameMode=ranked&season=");
		if (message == "{}")
		{
			return "false";
		}
		else
		{
			try
			{
				JSONObject json = new JSONObject(message);
				player_region = json.getString("region");
				JSONObject rankedranking_json = json.getJSONObject("rankedRanking");
				global_rankedranking = String.valueOf(rankedranking_json.getInt("global"));
				regional_rankedranking = String.valueOf(rankedranking_json.getInt("regional"));
				points_rankedranking = String.valueOf(rankedranking_json.getDouble("points")).replaceAll("[.][0-9]*", "");
				JSONObject ranked5v5ranking_json = json.getJSONObject("ranked5v5Ranking");
				global_ranked5v5ranking = String.valueOf(ranked5v5ranking_json.getInt("global"));
				regional_ranked5v5ranking = String.valueOf(ranked5v5ranking_json.getInt("regional"));
				points_ranked5v5ranking = String.valueOf(ranked5v5ranking_json.getDouble("points")).replaceAll("[.][0-9]*", "");
				JSONObject blitzranking_json = json.getJSONObject("blitzRanking");
				global_blitzranking = String.valueOf(blitzranking_json.getInt("global"));
				regional_blitzranking = String.valueOf(blitzranking_json.getInt("regional"));
				points_blitzranking = String.valueOf(blitzranking_json.getDouble("points")).replaceAll("[.][0-9]*", "");
			}
			catch (JSONException e)
			{
				System.out.println(e.toString());
			}
			List<List> info_list = new ArrayList<>();
			List<String> info1 =new ArrayList<String>();
			info1.add("服务大区: "+player_region+" ID: "+player_name);
			List<String> info2 =new ArrayList<String>();
			info2.add("说明索引");
			info2.add("3v3排位");
			info2.add("5v5排位");
			info2.add("闪电战");
			List<String> info3 =new ArrayList<String>();
			info3.add("排位分值");
			info3.add(points_rankedranking);
			info3.add(points_ranked5v5ranking);
			info3.add(points_blitzranking);
			List<String> info4 =new ArrayList<String>();
			info4.add("全服排名");
			info4.add(regional_rankedranking);
			info4.add(regional_ranked5v5ranking);
			info4.add(regional_blitzranking);
			List<String> info5 =new ArrayList<String>();
			info5.add("全球排名");
			info5.add(global_rankedranking);
			info5.add(global_ranked5v5ranking);
			info5.add(global_blitzranking);
			info_list.add(info1);
			info_list.add(info2);
			info_list.add(info3);
			info_list.add(info4);
			info_list.add(info5);
		
			

		   BufferedImage logo_img = Util.get_img("xml_ground54.png");
	    		BufferedImage ground_img = Util.get_random_img("Ranked_Tier");
			ImageUtil util = new ImageUtil(ground_img);
			util.drawwater(logo_img,0,0);
			BufferedImage createdimg = util.get_img();
			createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    String file_path = Util.save_img(createdimg);
			
			return file_path;
		}
	}
	
	
	public static String Sky_tier( String player_name, String player_region)
	{
		if(player_name == null){
			return "false";
		}
		int blitz= 0;
		int ranked = 0;
		int ranked_5v5 = 0;
		String blitz_Sky_info = "";
		String blitz_Sky_tier = "";
		String blitz_Sky_title = "";
		String ranked_Sky_info = "";
		String ranked_Sky_tier = "";
		String ranked_Sky_title = "";
		String ranked_5v5_Sky_info = "";
		String ranked_5v5_Sky_tier = "";
		String ranked_5v5_Sky_title = "";
		String message_to_send = "";
		List Controler_list= Util.get_database_contain("robot","Key","key");
		String vg_api_key =Controler_list.toArray()[0].toString();
		if (vg_api_key == "null")
		{
			return "false";
		}
		else
		{
			String message = Util.Auth_Curl("https://api.dc01.gamelockerapp.com/shards/" + player_region + "/players?filter[playerNames]=" + player_name, vg_api_key);
			if (message == null)
			{
				return "false";
			}
			else
			{
				try
				{
					JSONObject json = new JSONObject(message);
					JSONObject data = json.getJSONArray("data").getJSONObject(0).getJSONObject("attributes").getJSONObject("stats").getJSONObject("rankPoints");
					blitz = data.getInt("blitz");
					ranked = data.getInt("ranked");
					ranked_5v5 = data.getInt("ranked_5v5");


				}
				catch (JSONException e)
				{
					System.out.println(e.toString());
				}

				if (blitz == 0 ||blitz == -1)
				{
					blitz_Sky_tier = "1";
					blitz_Sky_title = "该咸鱼还没段位";
				}
				else
				{
					blitz_Sky_info = Util.Match_SkyTier(blitz);
					blitz_Sky_tier = blitz_Sky_info.split(" ")[0];
					blitz_Sky_title = blitz_Sky_info.split(" ")[1];
				}
				if (ranked == 0|| ranked == -1)
				{
					ranked_Sky_tier = "1";
					ranked_Sky_title = "该咸鱼还没段位";
				}
				else
				{
					ranked_Sky_info = Util.Match_SkyTier(ranked);
					ranked_Sky_tier = ranked_Sky_info.split(" ")[0];
					ranked_Sky_title = ranked_Sky_info.split(" ")[1];
				}
				if (ranked_5v5 == 0||ranked_5v5 == -1)
				{
					ranked_5v5_Sky_tier = "1";
					ranked_5v5_Sky_title = "该咸鱼还没段位";
				}
				else
				{
					ranked_5v5_Sky_info = Util.Match_SkyTier(ranked_5v5);
					ranked_5v5_Sky_tier = ranked_5v5_Sky_info.split(" ")[0];
					ranked_5v5_Sky_title = ranked_5v5_Sky_info.split(" ")[1];
				}
				
				List<List> info_list = new ArrayList<>();
				List<String> info1 =new ArrayList<String>();
				info1.add("服务大区: "+player_region+" ID: "+player_name);
				List<String> info2 =new ArrayList<String>();
				info2.add("3v3段位");
				info2.add("5v5段位");
				info2.add("闪电战段位");
				List<String> info3 =new ArrayList<String>();
				List<String> info4 =new ArrayList<String>();
				List<String> info5 =new ArrayList<String>();
				List<String> info6 =new ArrayList<String>();
				info6.add(ranked_Sky_title);
				info6.add(ranked_5v5_Sky_title);
				info6.add(blitz_Sky_title);
				List<String> info7 =new ArrayList<String>();
				info7.add(String.valueOf(ranked));
				info7.add(String.valueOf(ranked_5v5));
				info7.add(String.valueOf(blitz));
				info_list.add(info1);
				info_list.add(info2);
				info_list.add(info3);
				info_list.add(info4);
				info_list.add(info5);
				info_list.add(info6);
				info_list.add(info7);
			
				BufferedImage ranked_Sky_tier_img = Util.get_img("tier"+ranked_Sky_tier+".png");
				BufferedImage ranked_5v5_Sky_tier_img = Util.get_img("tier"+ranked_5v5_Sky_tier+".png");	
				BufferedImage blitz_Sky_tier_img = Util.get_img("tier"+blitz_Sky_tier+".png");
				
				BufferedImage logo_img = Util.get_img("xml_ranked.png");
	    		BufferedImage ground_img = Util.get_random_img("Sky_Tier");
			    ImageUtil util = new ImageUtil(ground_img);
				util.drawwater(logo_img,0,0);
				util.drawwater(ranked_Sky_tier_img,0,300);
			    util.drawwater(ranked_5v5_Sky_tier_img,450,300);
			    util.drawwater(blitz_Sky_tier_img,900,300);
			    
				BufferedImage createdimg = util.get_img();
			    createdimg = Util.create_xml_img(createdimg,info_list,new Color(139,0,0),40);
			    
			    String file_path = Util.save_img(createdimg);
				
				
				return file_path;
			}

		}
	}
	

}

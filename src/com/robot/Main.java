package com.robot;

import com.Tick_Tock.ANDROIDQQ.sdk.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Main implements Plugin
{

	private List<String> servers = Arrays.asList("cn na sg ea eu sa".split(" "));

	@Override
	public void onMessageHandler(FriendMessage p1)
	{
		
		// TODO: Implement this method
	}

	@Override
	public void onMessageHandler(TempolarMessage p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onMessageHandler(RequestMessage p1)
	{
		// TODO: Implement this method
	}
	
	
	@Override
	public String name()
	{
		// TODO: Implement this method
		return "虚荣机器人";
	}

	@Override
	public void onLoad(API _api)
	{
		this.api = _api;
	}

	
	private API api;

	public void log(String var0) {
		String var1 = (new SimpleDateFormat("[HH:mm:ss]")).format(new Date().getTime());
		System.out.println(var1 + " " + var0);
	}

	@Override
	public String Version() {
		return (String)null;
	}

	@Override
	public String author() {
		return (String)null;
	}

	@Override
	public void onMenu(GroupMessage qqmessage)
	{
		boolean qqgroupuin_found = false;
		List Workinggroup_list= Util.get_database_contain( "robot", "Workinggroup", "qqgroup");
		if (Workinggroup_list.toArray().length > 0)
		{
			for (Object qqnumber_recorded : Workinggroup_list)
			{
				if (qqnumber_recorded.toString().equals(String.valueOf(qqmessage.group_uin)))
				{
					MessageFactory factory = new MessageFactory();
					factory.Group_uin = qqmessage.group_uin;
					factory.message_type =0;
					BufferedImage logoImg = Util.get_img("help_water.png");
					BufferedImage groundImg = Util.get_random_img("Help");
					ImageUtil util = new ImageUtil(groundImg);
					util.drawwater(logoImg,0,0);
					BufferedImage createdimg = util.get_img();
					String file_path = Util.save_img(createdimg);
					factory.Message=file_path;
					this.api.SendGroupImage(factory);
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						System.out.println(e.toString());
					}
					factory.Message=Util.get_file_path_by_path("plugin/img/cmd-help.png");
					this.api.SendGroupImage(factory);
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						System.out.println(e.toString());
					}
					List<String> announce_content_list = Util.get_database_contain("robot","Announce_content","announcement");
					String announce_content =announce_content_list.get(0);
					List<String> announce_content_end_list = Util.get_database_contain("robot","Announce_end","announcement_end");
					String announce_content_end =announce_content_end_list.get(0);
					String message_to_send = Util.get_random_string(10) +"\n"+announce_content+"\n"+announce_content_end;
					factory.message_type=0;
					factory.Message=  message_to_send;
					this.api.SendGroupMessage(factory);
					return;
				}
			}
		}
		MessageFactory factory = new MessageFactory();
		factory.Group_uin = qqmessage.group_uin;
		factory.message_type =0;
		factory.Message=  "未开机，艾特我并且消息内容为开机二字即可开机";
		this.api.SendGroupMessage(factory);
	}

	
	@Override
	public void onMessageHandler(GroupMessage qqmessage)
	{
		
		boolean qqgroupuin_found = false;
		List Workinggroup_list= Util.get_database_contain( "robot", "Workinggroup", "qqgroup");
		if (Workinggroup_list.toArray().length > 0)
		{
			for (Object qqnumber_recorded : Workinggroup_list)
			{
				if (qqnumber_recorded.toString().equals(String.valueOf(qqmessage.group_uin)))
				{
					qqgroupuin_found = true;
				}
			}
		}
		staticalled(qqmessage);
		if (qqgroupuin_found)
		{
			enabled(qqmessage);
			//music(qqmessage);
			vainglory(qqmessage);
		}
		else
		{
			disabled(qqmessage);
		}
	}
	
	
	public void staticalled(GroupMessage qqmessage){
		boolean highest_authorization = qqmessage.sender_uin ==1721115102 || qqmessage.sender_uin ==403572613;
		MessageFactory factory = new MessageFactory();
		factory.Group_uin = qqmessage.group_uin;
		factory.message_type =0;
		if(qqmessage.message.matches("修改公告 .*")){
			if(highest_authorization){
				Util.update_to_database("robot","Announce_content","announcement",qqmessage.message.replaceAll("修改公告\\s+",""),"_id = 1");
				factory.Message=  "公告已修改";
				this.api.SendGroupMessage(factory);
			}else{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}
		}
		if(qqmessage.message.equals("查看授权")){
			if(highest_authorization){
				String result ="已授权群号:\n";
				List<String> group_list= Util.get_database_contain("robot","Controller","groupuin");
				for(String groupuin : group_list){
					result += Util.ridiculous_uin(groupuin)+"\n";
				}
				factory.Message=  result;
				this.api.SendGroupMessage(factory);
			}else{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}
		}
		if(qqmessage.message.matches("查看授权\\s+.*")){
			if(highest_authorization){
				
				factory.Message=  Util.read_controllers(qqmessage.message.split("\\s+")[1]);
				this.api.SendGroupMessage(factory);
			}else{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}
		}
		if(qqmessage.message.matches("运行监控")){
			List Controler_list= Util.get_database_contain("robot","Workinggroup","qqgroup");

			if(highest_authorization){
				String result ="已开机群列表:\n";
				for(Object qquin : Controler_list){
					result += Util.ridiculous_uin(String.valueOf(qquin))+"\n";
				}
				factory.Message=  result;
				this.api.SendGroupMessage(factory);
			}else{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}
		}
		if(qqmessage.message.matches("修改公告尾巴 .*")){
			if(highest_authorization){
				Util.update_to_database("robot","Announce_end","announcement_end",qqmessage.message.replaceAll("修改公告尾巴\\s+",""),"_id = 1");
				factory.Message=  "公告尾巴已修改";
				this.api.SendGroupMessage(factory);
			}else{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}
		}
		
		if (qqmessage.message.matches("开机 .*"))
		{
			if (highest_authorization)
			{
				String groupnumber = "";
				String[] message_list = qqmessage.message.split("\\s+");
				if (message_list.length >= 2)
				{
					groupnumber = message_list[1];
				}

				boolean qquin_found = false;
				List Controler_list= Util.get_database_contain("robot", "Workinggroup", "qqgroup");

				for (Object qqnumber_recorded : Controler_list)
				{
					if (qqnumber_recorded.toString().equals(groupnumber))
					{
						qquin_found = true;
					}
				}
				if (qquin_found == false)
				{
					
					Util.add_to_database("robot","Workinggroup","qqgroup",groupnumber);
					factory.Message=  "开机成功";
					this.api.SendGroupMessage(factory);

				}
				else
				{
					factory.Message=  "开机失败:无法重复开机";
					this.api.SendGroupMessage(factory);
				}
			}
			else
			{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}

		}
		if (qqmessage.message.matches("关机 .*"))
		{
			if (highest_authorization)
			{
				String groupnumber = "";
				String[] message_list = qqmessage.message.split("\\s+");
				if (message_list.length >= 2)
				{
					groupnumber = message_list[1];
				}

				boolean qquin_found = false;
				List Controler_list= Util.get_database_contain("robot", "Workinggroup", "qqgroup");

				for (Object qqnumber_recorded : Controler_list)
				{
					if (qqnumber_recorded.toString().equals(groupnumber))
					{
						qquin_found = true;
					}
				}
				if (qquin_found == false)
				{

					
					factory.Message=  "关机失败，未开机何来关机之说";
					this.api.SendGroupMessage(factory);

				}
				else
				{
					Util.delete_from_database("robot","Workinggroup","qqgroup="+groupnumber);
					factory.Message=  "关机成功";
					this.api.SendGroupMessage(factory);
				}
			}
			else
			{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}

		}
		if (qqmessage.message.matches("添加授权 .*"))
		{
			if (highest_authorization)
			{
				factory.Message = Util.add_controler(qqmessage.message.split("\\s+")[1],qqmessage.message.split("\\s+")[2]);
				this.api.SendGroupMessage(factory);
			}
			else
			{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}

		}


		if (qqmessage.message.matches("删除授权\\s+.*"))
		{
			if (highest_authorization)
			{


				factory.Message=  Util.remove_controler(qqmessage.message.split(" ")[1],qqmessage.message.split(" ")[2]);
				this.api.SendGroupMessage(factory);

			}
			else
			{
				factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
			}

		}

		if (qqmessage.message.matches("查看公告")){
			List<String> announce_content_list = Util.get_database_contain("robot","Announce_content","announcement");
			String announce_content =announce_content_list.get(0);
			List<String> announce_content_end_list = Util.get_database_contain("robot","Announce_end","announcement_end");
			String announce_content_end =announce_content_end_list.get(0);
			String message_to_send = Util.get_random_string(10) +"\n"+announce_content+"\n"+announce_content_end;
			factory.Message=  message_to_send;
			this.api.SendGroupMessage(factory);


		}
		if (qqmessage.message.matches("运行状态")){
			boolean qqgroupuin_found = false;
			List Controler_list= Util.get_database_contain( "robot", "Workinggroup", "qqgroup");
			if (Controler_list.toArray().length > 0)
			{
				for (Object qqnumber_recorded : Controler_list)
				{
					if (qqnumber_recorded.toString().equals(String.valueOf(qqmessage.group_uin)))
					{
						qqgroupuin_found = true;
					}
				}
			}
			String isrunning = "未开机";
			if (qqgroupuin_found){
				isrunning = "已开机";
			}
            SimpleDateFormat format0 = new SimpleDateFormat("MM-dd HH:mm:ss");
			String message_to_send = "机器人运行状态\n随机字符:  "+Util.get_random_string(10)+"\n当前状态:  "+isrunning+"\n启动时间:  "+format0.format(this.api.GetInitiateTime())+"\n最后自动登录时间:  "+format0.format(this.api.GetLastLoginTime());

			factory.Message=  message_to_send;
			this.api.SendGroupMessage(factory);


		}
		
	}


	public void disabled(GroupMessage qqmessage){
		
		MessageFactory factory = new MessageFactory();
		factory.Group_uin = qqmessage.group_uin;
		factory.message_type =0;
		if (qqmessage.at_list.size() != 0){
			if (qqmessage.at_list.contains(qqmessage.self_uin) )
			{
				if (qqmessage.message.matches(".*开机"))
				{
					if (Util.is_controller(qqmessage.group_uin,qqmessage.sender_uin))
					{
						Util.add_to_database("robot","Workinggroup","qqgroup",String.valueOf(qqmessage.group_uin));
						factory.Message=  "机器人已开机，发送\"菜单 "+this.name()+"\"我查看功能列表。";
				        this.api.SendGroupMessage(factory);
					}
					else
					{
						factory.Message=  "没有权限开机，如果想要开关机权限，请私聊机器人，作者有空会回复，仅限群主/管理员可以获取开关机权限";
						this.api.SendGroupMessage(factory);
					}
				}
				else
				{
					factory.Message=  "未开机，艾特我并且消息内容为开机二字即可开机";
					this.api.SendGroupMessage(factory);
				}
			}
			
		}
		
		


	}

	public void enabled(GroupMessage qqmessage){
		MessageFactory factory = new MessageFactory();
		factory.Group_uin = qqmessage.group_uin;
		factory.message_type =0;
		if (qqmessage.at_list.size() != 0){
		if (qqmessage.at_list.contains(qqmessage.self_uin) )
		{
			if(qqmessage.message.matches(".*关机")){
				
				if (Util.is_controller(qqmessage.group_uin,qqmessage.sender_uin)){
					Util.delete_from_database("robot","Workinggroup","qqgroup="+String.valueOf(qqmessage.group_uin));
					factory.Message=  "已关机";
				this.api.SendGroupMessage(factory);
				}else{
					factory.Message=  "拒绝访问";
				this.api.SendGroupMessage(factory);
				}
			}
		}
      }
	}




	public void vainglory(GroupMessage qqmessage){
		MessageFactory factory = new MessageFactory();
		factory.Group_uin = qqmessage.group_uin;
		factory.message_type =0;
		if(qqmessage.message.matches("查询\\s+.*")){
			String cmd = qqmessage.message.replaceAll("^查询\\s+","");
			
			String[] args = cmd.split("\\s+");
			
			if(args.length==0){
				return;
			}
			
			int possition = 0;
			
			String mode = "none";
			String player_name = null;
			String page = "0";
			String server = "cn";
			String type = "";
			String sort = "胜率";
			int sort_mode=1;
			while(possition < args.length){
				try{
					switch(args[possition]){
						case("-n"):{
								possition+=1;
								player_name = args[possition];
							}
							break;
						case("-p"):{
								possition+=1;
								page = args[possition];
								if(!page.matches("^[0-9]*$")){
									factory.Message="页数只可以写数字";
									this.api.SendGroupMessage(factory);
									return;
								}
							}
							break;
						case("-s"):{
								possition+=1;
								server = args[possition];
								if(!this.servers.contains(server)){
									factory.Message=  "服务区错误，只可选cn(中国) na(北美) sg(东南亚) ea(东亚) eu(东欧) sa(南美)";
									this.api.SendGroupMessage(factory);
									return;
								}
							}
							break;
						case("-S"):{
								possition+=1;
								sort = args[possition];
								List<String> sorts = Arrays.asList("胜率 扳率 选率".split(" "));
								if(!sorts.contains(sort)){
									factory.Message=  "排序错误，只可选胜率 扳率 选率";
									this.api.SendGroupMessage(factory);
									return;
								}
								if (mode.equals("胜率"))
								{
									sort_mode = 1;
								}
								else if (mode.equals("扳率"))
								{
									sort_mode = 2;
								}
								else if (mode.equals("选率"))
								{
									sort_mode = 3;
								}
							}
							break;
						case("-t"):{
								possition+=1;
								type = args[possition];
							}
							break;
						case("-i"):{
								mode = "player_info";//信息
							}
							break;
						case("-I"):{
								mode = "player_detail_info";//详细信息
							}
							break;
						case("-r"):{
								mode = "player_ranke";//段位
							}
							break;
						case("-R"):{
								mode = "player_sky_ranke";//天梯排行
							}
							break;
						case("-P"):{
								mode = "player_ranke_ranking";//段位排行
							}
							break;
						case("-m"):{
								mode = "player_match";//对局
							}
							break;
						case("-M"):{
								mode = "player_detail_match";//详细对局
							}
							break;
						case("-h"):{
								mode = "player_hero";//英雄
							}
							break;
						case("-H"):{
								mode = "hero_ranke";//英雄排行
							}
							break;
						case("-g"):{
								mode = "player_game";//战绩
							}
							break;
						case("-G"):{
								mode = "player_detail_game";//详细战绩
							}
							break;
						case("-T"):{
								mode = "time";//时光
							}
							break;
						case("-Help"):{
								mode = "help";//帮助
							}
							break;
						default:{
								factory.Message="未知命令: "+args[possition];
								this.api.SendGroupMessage(factory);
								return;
							}

					}

				}catch(ArrayIndexOutOfBoundsException e){
					factory.Message="命令短于期待长度";
					this.api.SendGroupMessage(factory);
					return;
				}
				
				possition+=1;
			}
			
			String message_to_send = "";
			switch(mode){
				case("none"):{
						factory.Message="没有指定查询命令";
						this.api.SendGroupMessage(factory);
						return;
					}
				case("player_info"):{
						message_to_send=VaingloryFactory.Player_info(player_name, server);
					}
					break;
				case("player_detail_info"):{
						message_to_send=VaingloryFactory.Player_info_detail(player_name);
					}
					break;
				case("player_ranke"):{
						message_to_send=VaingloryFactory.Sky_tier(player_name, server);
					}
					break;
				case("player_sky_ranke"):{
					if(type.equals("")){
						type="3v3排位";
					}
						List<String> types = Arrays.asList("5v5排位 3v3排位 闪电战".split(" "));
						if(!types.contains(type)){
							factory.Message=  "排序错误，只可选5v5排位 3v3排位 闪电战";
							this.api.SendGroupMessage(factory);
							return;
						}
						message_to_send=VaingloryFactory.Rank_player(player_name, Util.translate_game_mode(type).toLowerCase().replace("%20", ""), server);
					}
					break;
				case("player_ranke_ranking"):{
						message_to_send=VaingloryFactory.Rank_tier(player_name);
					}
					break;
				case("player_match"):{
						message_to_send=VaingloryFactory.Match_detail_img(player_name,page);
					}
					break;
				case("player_detail_match"):{
						message_to_send=VaingloryFactory.Match_super_detail_img(player_name,page);
					}
					break;
				case("player_hero"):{
						message_to_send=VaingloryFactory.Heroes_detail(player_name,page);
					}
					break;
				case("hero_ranke"):{
						message_to_send=VaingloryFactory.Heroes_rank(sort_mode, page,server);
					}
					break;
				case("player_game"):{
						List<String> types = Arrays.asList("3v3匹配 3v3排位 5v5匹配 5v5排位 闪电战 大乱斗".split(" "));
						if(!types.contains(type)&&!type.equals("")){
							factory.Message=  "排序错误，只可选3v3匹配 3v3排位 5v5匹配 5v5排位 闪电战 大乱斗";
							this.api.SendGroupMessage(factory);
							return;
						}
						message_to_send=VaingloryFactory.Game_history(player_name,Util.translate_game_mode(type), page);
					}
					break;
				case("player_detail_game"):{
						message_to_send=VaingloryFactory.Match_history_img(player_name, page);
					}
					break;
				case("time"):{
						message_to_send=VaingloryFactory.Timer(player_name);
					}
					break;
				case("help"):{
						message_to_send=Util.get_file_path_by_path("plugin/img/cmd-help.png");
					}
					break;
					default:{
							message_to_send="false";
					}
					
			}
			
			if (message_to_send.equals("false")){
				factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

			}else{
				factory.Message = message_to_send;
				factory.message_type=2;
				this.api.SendGroupImage(factory);
			}
			
			
		}
		
		
		
		
		
		
		
		
		if (qqmessage.message.matches("详细信息\\s+.*"))
		{
			boolean region_matched =true;
			String player_region = "cn";
			String player_name = null;
			String[] message_list = qqmessage.message.split("\\s+");
			String message_to_send = "";
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				region_matched = false;
				player_region = message_list[2];
				String[] all_region="cn na sg ea eu sa".split(" ");
				for (String region :all_region)
				{
					if (region.equals(player_region))
					{
						region_matched = true;
					}
				}
			}
			else
			{
                player_region = "cn";
                region_matched = true;
			}
			if (region_matched)
			{


				message_to_send=VaingloryFactory.Player_info_detail(player_name);
				if (message_to_send.equals("false")){
					factory.Message="未查询到数据";
					this.api.SendGroupMessage(factory);

				}else{
					factory.Message = message_to_send;
					factory.message_type=2;
					this.api.SendGroupImage(factory);
				}

			}
			else
			{
				factory.Message="服务区错误";
				this.api.SendGroupMessage(factory);
			}

		}
		
		if (qqmessage.message.matches("详细对局 .*"))
		{
			String[] message_list = qqmessage.message.split("\\s+");
			String player_name = null;
			String page = "0";
			boolean segment_false = false;
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				page = message_list[2];
				if (Pattern.compile("[^0-9]").matcher(page).find())
				{
					segment_false = true;
				}
			}
			if (segment_false)
			{
				factory.Message="第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
			}
			else
			{


				String message_to_send = VaingloryFactory.Match_super_detail_img( player_name, page);
					if (message_to_send.equals("false"))
				{
					factory.Message="未查询到数据";
					this.api.SendGroupMessage(factory);
				}
				else
				{
					factory.Message = message_to_send;
					factory.message_type=2;
					this.api.SendGroupImage(factory);
				}

			}
		}
		
		if (qqmessage.message.matches("详细战绩 .*"))
		{
			String[] message_list = qqmessage.message.split("\\s+");
			String player_name = null;
			String page = "0";
			boolean segment_false = false;
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				page = message_list[2];
				if (Pattern.compile("[^0-9]").matcher(page).find())
				{
					segment_false = true;
				}
			}
			if (segment_false)
			{
				factory.Message="第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
			}
			else
			{

				String message_to_send=VaingloryFactory.Match_history_img(player_name, page);
				if (message_to_send.equals("false"))
				{
					factory.Message="未查询到数据";
					this.api.SendGroupMessage(factory);
				}
				else
				{
				    factory.Message = message_to_send;
					factory.message_type=2;
					this.api.SendGroupImage(factory);
				}
			}
		}
		if (qqmessage.message.matches("对局 .*"))
		{
			String[] message_list = qqmessage.message.split("\\s+");
			String player_name = null;
			String page = "0";
			boolean segment_false = false;
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				page = message_list[2];
				if (Pattern.compile("[^0-9]").matcher(page).find())
				{
					segment_false = true;
				}
			}
			if (segment_false)
			{
				factory.Message="第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
			}
			else
			{


				String message_to_send = VaingloryFactory.Match_detail_img(player_name, page);
				if (message_to_send.equals("false"))
				{
					factory.Message="未查询到数据";
					this.api.SendGroupMessage(factory);

				}
				else
				{
					factory.Message = message_to_send;
					factory.message_type=2;
					this.api.SendGroupImage(factory);

				}

			}
		}
		
		
		
		
		if (qqmessage.message.matches("天梯排名 .*"))
		{
			String[] message_list = qqmessage.message.split("\\s+");
			String game_mode = "3v3排位";
			String player_name = "";
			int sort_mode = 1;
			String page = "1";
			boolean segment_false = false;
			boolean game_mode_matched = true;
			boolean region_matched = true;
			String player_region = "cn";
			String message_to_send = "";
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				game_mode = message_list[2];
				game_mode_matched = false;
                String[] all_game_mode = "3v3排位 5v5排位 闪电战".split(" ");
                for (String mode : all_game_mode)
				{
                    if (mode.equals(game_mode))
					{
                        game_mode_matched = true;
                    }
                }

			}
			game_mode = Util.translate_game_mode(game_mode).toLowerCase().replace("%20", "");

			if (message_list_length >= 4)
			{
				region_matched = false;
				player_region = message_list[3];
				String[] all_region="cn na sg ea eu sa all".split(" ");
				for (String region :all_region)
				{
					if (region.equals(player_region))
					{
						region_matched = true;
					}
				}
			}
			else
			{
                player_region = "cn";
                region_matched = true;
			}
			if (segment_false)
			{
				factory.Message=  "第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
			}
			else
			{
				if (game_mode_matched == false)
				{
					factory.Message=  "排序类型错误，只可选:5v5排位 3v3排位 闪电战";
				this.api.SendGroupMessage(factory);
				}
				else
				{
				    if (region_matched == false)
					{
						factory.Message=  "服务区错误，只可选cn na sg ea eu sa";
				this.api.SendGroupMessage(factory);
					}
					else
					{
						
							message_to_send=VaingloryFactory.Rank_player(player_name, game_mode, player_region);
							
							if (message_to_send.equals("false")){
								factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

							
						}else{
						factory.Message = message_to_send;
						factory.message_type=2;
							this.api.SendGroupImage(factory);
						
						}
					}
				}
			}
		}
		if (qqmessage.message.matches("英雄排名 .*"))
		{
			String[] message_list = qqmessage.message.split("\\s+");
			String mode = null;
			int sort_mode = 1;
			String page = "1";
			boolean segment_false = false;
			boolean mode_matched = false;
			boolean region_matched = true;
			String player_region = "cn";
			String message_to_send = "";
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				mode = message_list[1];
				if (mode.equals("胜率"))
				{
					sort_mode = 1;
					mode_matched = true;
				}
				else if (mode.equals("扳率"))
				{
					sort_mode = 2;
					mode_matched = true;
				}
				else if (mode.equals("选率"))
				{
					sort_mode = 3;
					mode_matched = true;
				}

			}
			if (message_list_length >= 3)
			{
				page = message_list[2];
				if (Pattern.compile("[^0-9]").matcher(page).find())
				{
					segment_false = true;
				}
			}
			if (message_list_length >= 4)
			{
				region_matched = false;
				player_region = message_list[3];
				String[] all_region="cn na sg ea eu sa".split(" ");
				for (String region :all_region)
				{
					if (region.equals(player_region))
					{
						region_matched = true;
					}
				}
			}
			else
			{
                player_region = "cn";
                region_matched = true;
			}
			if (segment_false)
			{
				factory.Message=  "第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
			}
			else
			{
				if (mode_matched == false)
				{
					factory.Message=  "排序类型错误，只可选:胜率 扳率 选率";
				this.api.SendGroupMessage(factory);
				}
				else
				{
				    if (region_matched == false)
					{
						factory.Message=  "服务区错误，只可选cn na sg ea eu sa";
				this.api.SendGroupMessage(factory);
					}
					else
					{
					    
							message_to_send=VaingloryFactory.Heroes_rank(sort_mode, page, player_region);
							if (message_to_send.equals("false")){
								factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

							}else{
								factory.Message = message_to_send;
								factory.message_type=2;
								this.api.SendGroupImage(factory);

							}
						
					}
				}
			}


		}
		if (qqmessage.message.matches("英雄 .*"))
		{
			String[] message_list = qqmessage.message.split("\\s+");
			String player_name = null;
			String page = "0";
			boolean segment_false = false;
			int message_list_length = message_list.length;
			String message_to_send = "";
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				page = message_list[2];
				if (Pattern.compile("[^0-9]").matcher(page).find())
				{
					segment_false = true;
				}
			}
			if (segment_false)
			{
				factory.Message=  "第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
			}
			else
			{
				
					message_to_send=VaingloryFactory.Heroes_detail(player_name, page);
					if (message_to_send.equals("false")){
						factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

					}else{
						factory.Message = message_to_send;
						factory.message_type=2;
						this.api.SendGroupImage(factory);

					}
				
			}



		}
		if (qqmessage.message.matches("战绩 .*"))
		{
			int command = 1;
			if (qqmessage.message.matches("战绩 .*"))
			{
				command = 1;
			}
			else
			{
				command = 2;
			}
			String[] message_list = qqmessage.message.split("\\s+");
			String player_name = null;
			String page = "0";
			String game_mode = "";
			boolean segment_false = false;
			boolean game_mode_matched = true;
			String message_to_send = "";
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				page = message_list[2];
				if (Pattern.compile("[^0-9]").matcher(page).find())
				{
					segment_false = true;
				}
			}
			if (message_list_length >= 4)
			{
                game_mode = message_list[3];
				game_mode_matched = false;
                String[] all_game_mode = "3v3匹配 3v3排位 5v5匹配 5v5排位 闪电战 大乱斗".split(" ");
                for (String mode : all_game_mode)
				{
                    if (mode.equals(game_mode))
					{
                        game_mode_matched = true;
                    }
                }
                game_mode = Util.translate_game_mode(game_mode);
            }
			else
			{
                game_mode = "";
                game_mode_matched = true;
            }

			if (game_mode_matched == false)
			{
				factory.Message=  "游戏类型错误，只可用:3v3匹配 3v3排位 5v5匹配 5v5排位 闪电战 大乱斗";
				this.api.SendGroupMessage(factory);
			}
			else
			{

				if (segment_false)
				{
					factory.Message=  "第二个参数只可以写数字";
				this.api.SendGroupMessage(factory);
				}
				else
				{

						message_to_send=VaingloryFactory.Game_history(player_name, game_mode, page);
						if (message_to_send.equals("false")){
							factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

						}else{
							factory.Message = message_to_send;
							factory.message_type=2;
							this.api.SendGroupImage(factory);

						}
					
				}
			}
		}

		if (qqmessage.message.matches("段位排名 .*"))
        {
			String[] message_list = qqmessage.message.split("\\s+");
			String player_name =null;
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			String message_to_send = VaingloryFactory.Rank_tier(player_name);
			if (message_to_send.equals("false")){
				factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

			}else{
				factory.Message = message_to_send;
				factory.message_type=2;
				this.api.SendGroupImage(factory);

			}
		}
		if (qqmessage.message.matches("段位 .*"))
		{
			boolean region_matched =true;
			String player_region = "cn";
			String player_name = null;
			String[] message_list = qqmessage.message.split("\\s+");
			String message_to_send = "";
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				region_matched = false;
				player_region = message_list[2];
				String[] all_region="cn na sg ea eu sa".split(" ");
				for (String region :all_region)
				{
					if (region.equals(player_region))
					{
						region_matched = true;
					}
				}
			}
			else
			{
                player_region = "cn";
                region_matched = true;
			}
			if (region_matched)
			{
				
					message_to_send=VaingloryFactory.Sky_tier(player_name, player_region);
					if (message_to_send.equals("false")){
						factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

					}else{
						factory.Message = message_to_send;
						factory.message_type=2;
						this.api.SendGroupImage(factory);

					}
				
			}
			else
			{
				factory.Message=  "服务区错误";
				this.api.SendGroupMessage(factory);
			}
		}

		if (qqmessage.message.matches("时光 .*"))
		{
			String player_name = null;
			String[] message_list = qqmessage.message.split("\\s+");
		    int message_list_length = message_list.length;
			String message_to_send = "";
		    if (message_list_length >= 2)
		    {
				player_name = message_list[1];
		    }
		
				message_to_send=VaingloryFactory.Timer(player_name);
				if (message_to_send.equals("false")){
					factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);
				}else{
					factory.Message = message_to_send;
					factory.message_type=2;
					this.api.SendGroupImage(factory);

				}
			
		}
		if (qqmessage.message.matches("信息 .*"))
		{
			boolean region_matched =true;
			String player_region = "cn";
			String player_name = null;
			String[] message_list = qqmessage.message.split("\\s+");
			String message_to_send = "";
			int message_list_length = message_list.length;
			if (message_list_length >= 2)
			{
				player_name = message_list[1];
			}
			if (message_list_length >= 3)
			{
				region_matched = false;
				player_region = message_list[2];
				String[] all_region="cn na sg ea eu sa".split(" ");
				for (String region :all_region)
				{
					if (region.equals(player_region))
					{
						region_matched = true;
					}
				}
			}
			else
			{
                player_region = "cn";
                region_matched = true;
			}
			if (region_matched)
			{
				
					message_to_send=VaingloryFactory.Player_info(player_name, player_region);
					if (message_to_send.equals("false")){
						factory.Message=  "查询失败";
				this.api.SendGroupMessage(factory);

					}else{
						factory.Message = message_to_send;
						factory.message_type=2;
						this.api.SendGroupImage(factory);

					}
				
			}
			else
			{
				factory.Message=  "服务区错误";
				this.api.SendGroupMessage(factory);
			}

		}



	}



	
	
	
	
}


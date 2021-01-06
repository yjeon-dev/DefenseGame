package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Rank extends JFrame implements ActionListener {
   // 클래스 Rank JFrame 상속하고 이벤트 발생을 위해 액션리스너 상속
   JLabel jlbMain;

   JButton jbtnRank, jbtnName, jbtnRound, jbtnOp, jbtnScore;
   JButton jbtnHome;
   JButton jbtnOne, jbtnTwo, jbtnThree, jbtnFour, jbtnFive;
   
   JLabel jround, jattack, jscore;

//   JTextField jtfName1, jtfName2, jtfName3, jtfName4, jtfName5;
//   JTextField jtfRound1, jtfRound2, jtfRound3, jtfRound4, jtfRound5;
//   JTextField jtfOp1, jtfOp2, jtfOp3, jtfOp4, jtfOp5;
//   JTextField jtfScore1, jtfScore2, jtfScore3, jtfScore4, jtfScore5;
//   

   // 텍스트필드는 수정가능하므로
   ImageIcon imgMain;
   ImageIcon imgRank, imgName, imgRound, imgOp, imgScore;
   ImageIcon imgOne, imgTwo, imgThree, imgFour, imgFive;
   ImageIcon imgHome;

   String nick;
   int round;
   int atk;
   int score;
   static String uid;

   // 이미지,버튼,라벨 이름 생성
   Rank() {
      setLayout(null);
      // 레이 아웃 비우기
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      // 윈도우창 종료시 (프로세스 종료)

      imgMain = new ImageIcon("src/images/RankMain.png");
      imgHome = new ImageIcon("src/images/RankHome.png");
      // 메인 이미지와 홈 이미지 추가

      imgRank = new ImageIcon("src/images/Ranking.png");
      imgName = new ImageIcon("src/images/Nm.png");
      imgRound = new ImageIcon("src/images/Round.png");
      imgOp = new ImageIcon("src/images/Op.png");
      imgScore = new ImageIcon("src/images/Score.png");
      // 랭킹,닉네임,라운드,공격력,총공격력 이미지 추가

      imgOne = new ImageIcon("src/images/One.png");
      imgTwo = new ImageIcon("src/images/Two.png");
      imgThree = new ImageIcon("src/images/Three.png");
      imgFour = new ImageIcon("src/images/Four.png");
      imgFive = new ImageIcon("src/images/Five.png");
      // 랭크 이미지 추가

      jbtnHome = new JButton(imgHome);

      jbtnOne = new JButton(imgOne);
      jbtnTwo = new JButton(imgTwo);
      jbtnThree = new JButton(imgThree);
      jbtnFour = new JButton(imgFour);
      jbtnFive = new JButton(imgFive);

      jbtnRank = new JButton(imgRank);
      jbtnName = new JButton(imgName);
      jround = new JLabel(imgRound);
      jattack = new JLabel(imgOp);
      jscore = new JLabel(imgScore);
      // 버튼 클래스 이미지 추가 생성
      jlbMain = new JLabel(imgMain);
      // 라벨에 메인 이미지와 랭크 이미지 붙임

//      //JTextField jtf[];
//      //jtf = new JTextField[4];
//      //for (int i = 0; i < 4; i++) {
//      //   jtf[i] = new JTextField();
//         
//      -

      JTextField nick[] = new JTextField[5];
      JTextField round[] = new JTextField[5];
      JTextField atk[] = new JTextField[5];
      JTextField score[] = new JTextField[5];

      for (int i = 0; i <= 4; i++) {
         nick[i] = new JTextField();
         round[i] = new JTextField();
         atk[i] = new JTextField();
         score[i] = new JTextField();

         Font f = new Font("굴림체", Font.BOLD, 30);
         nick[i].setFont(f);
         round[i].setFont(f);
         atk[i].setFont(f);
         score[i].setFont(f);
         
         
         nick[i].setHorizontalAlignment(nick[i].CENTER);
         round[i].setHorizontalAlignment(nick[i].CENTER);
         atk[i].setHorizontalAlignment(nick[i].CENTER);
         score[i].setHorizontalAlignment(nick[i].CENTER);

      }

//      jtf[0] 1, jtf[0]jtf[0]jtf[0]
//      jtf[1]
//      jtf[2]
//      jtf[3]

      // 버튼 클래스 객체 생성

//      jtfName1 = new JTextField();
//      jtfName2 = new JTextField();
//      jtfName3 = new JTextField();
//      jtfName4 = new JTextField();
//      jtfName5 = new JTextField();

      // 이름 필드 가운데 정렬

//      jtfRound1 = new JTextField();
//      jtfRound2 = new JTextField();
//      jtfRound3 = new JTextField();
//      jtfRound4 = new JTextField();
//      jtfRound5 = new JTextField();

      // 라운드 필드 가운데 정렬

//      jtfOp1 = new JTextField();
//      jtfOp2 = new JTextField();
//      jtfOp3 = new JTextField();
//      jtfOp4 = new JTextField();
//      jtfOp5 = new JTextField();

      // 공격력 텍스트 필드 가운데 정렬

//      jtfScore1 = new JTextField();
//      jtfScore2 = new JTextField();
//      jtfScore3 = new JTextField();
//      jtfScore4 = new JTextField();
//      jtfScore5 = new JTextField();
//      // 총공격력 텍스트 필드 생성

      jbtnHome.setBounds(670, 520, 120, 50);
      // 메인으로 라는 글자 위치 지정
      jbtnRank.setBounds(60, 120, 90, 40);
      jbtnName.setBounds(150, 120, 100, 40);
      jround.setBounds(250, 120, 130, 40);
      jattack.setBounds(380, 120, 140, 40);
      jscore.setBounds(520, 120, 180, 40);
      //
      jlbMain.setBounds(0, 0, 800, 600);
      // 랭킹이라는 글자 위치 지정

      // 랭크,닉네임,라운드,공격력,총공격력 위치 지정
      jbtnOne.setBounds(60, 160, 90, 70);
      jbtnTwo.setBounds(60, 230, 90, 70);
      jbtnThree.setBounds(60, 300, 90,70);
      jbtnFour.setBounds(60, 370, 90, 70);
      jbtnFive.setBounds(60, 440, 90, 70);
      // 랭크 버튼 위치 지정

      nick[0].setBounds(150, 160, 100, 70);
      nick[1].setBounds(150, 230, 100, 70);
      nick[2].setBounds(150, 300, 100, 70);
      nick[3].setBounds(150, 370, 100, 70);
      nick[4].setBounds(150, 440, 100, 70);
      // 이름텍스트 필드 위치 지정

      round[0].setBounds(250, 160, 130, 70);
      round[1].setBounds(250, 230, 130, 70);
      round[2].setBounds(250, 300, 130, 70);
      round[3].setBounds(250, 370, 130, 70);
      round[4].setBounds(250, 440, 130, 70);
      // 라운드 텍스트 필드 위치 지정

      atk[0].setBounds(380, 160, 140, 70);
      atk[1].setBounds(380, 230, 140, 70);
      atk[2].setBounds(380, 300, 140, 70);
      atk[3].setBounds(380, 370, 140, 70);
      atk[4].setBounds(380, 440, 140, 70);
//      // 공격력 텍스트 필드 위치 지정

      score[0].setBounds(520, 160, 180, 70);
      score[1].setBounds(520, 230, 180, 70);
      score[2].setBounds(520, 300, 180, 70);
      score[3].setBounds(520, 370, 180, 70);
      score[4].setBounds(520, 440, 180, 70);
      // 총공격력 텍스트 필드 위치 지정

      add(jbtnHome);
      add(jbtnRank);
      add(jbtnName);
      add(jround);
      add(jattack);
      add(jscore);
      // 버튼추가

      add(nick[0]);
      add(nick[1]);
      add(nick[2]);
      add(nick[3]);
      add(nick[4]);
      // 이름 텍스트 필드 추가

      add(round[0]);
      add(round[1]);
      add(round[2]);
      add(round[3]);
      add(round[4]);
      // 라운드 텍스트 필드 추가

      add(atk[0]);
      add(atk[1]);
      add(atk[2]);
      add(atk[3]);
      add(atk[4]);
      // 공격력 텍스트 필드 추가
      add(score[0]);
      add(score[1]);
      add(score[2]);
      add(score[3]);
      add(score[4]);
      // 총공격 텍스트 필드 추가

      // 랭크 라벨 추가
      add(jbtnOne);
      add(jbtnTwo);
      add(jbtnThree);
      add(jbtnFour);
      add(jbtnFive);
      // 라벨과 버튼에 이미지 추가 붙임

      add(jlbMain);
      // 라벨에 이미지 추가
      
      for(int i = 0; i<5; i ++) {
          nick[i].setEditable(false);
          round[i].setEditable(false);
          atk[i].setEditable(false);
          score[i].setEditable(false);
       }

      setTitle("Defense Game");
      // 윈도우창 제목 지정
      setBounds(560, 240, 800, 600);
      // 윈도우창 위치와 크기 지정
      setVisible(true);
      // 윈도우창 보이게 구현
      setResizable(false);
      // 윈도우 창 크기조정 못하게 고정

      // 이벤트 처리 감지
      jbtnHome.addActionListener(this);

      User_DataDAO dao = new User_DataDAO();
      ArrayList<User_DataVO> list = dao.selectrank();

      for (int i = 0; i < list.size(); i++) {

         User_DataVO x = list.get(i);

         nick[i].setText(x.getNick());

         round[i].setText(String.valueOf(x.getRound()));

         atk[i].setText(String.valueOf(x.getAtk()));

         score[i].setText(String.valueOf(x.getScore()));

//         System.out.println(x.getNick() + "   ");
//         System.out.print(x.getRound());
//         System.out.print(x.getAtk());
//         System.out.println(x.getScore());
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // 액션 퍼포먼트 함수 사용
      Object obj = e.getSource();
      // 이벤트 발생할때 필요한 타입으로 변환 및 반환시켜줌
      if (obj == jbtnHome) {
         // jbtnHome 버튼을 클릭시
         this.setVisible(false);
         // 화면구현 안함
         new Main();
         // 메인 메소드 불러옴
         //this.setVisible(false);
      }
   }

   public static void main(String[] args) {
      new Rank();

   }

}
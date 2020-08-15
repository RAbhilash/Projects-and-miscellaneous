#include<fstream.h>
#include<conio.h>
#include<stdio.h>
#include<string.h>
#include<process.h>
#include<stdlib.h>
#include<graphics.h>

class student
{
	char id[10];
	char name[20],adrs[20];
	float marks;
	public:
	void input();
        void billgen();
	void output();
	int getrn();
	char *getname();
	float getmarks();
	void setrn(int);
	void setname(char []);
	void setmarks(float);
};
void student::input()//1st input...
{
   int gd = DETECT, gm, x = 25, y = 25, font = 10;

   initgraph(&gd,&gm,"C:\\TC\\BGI");



      settextstyle(font, HORIZ_DIR, 1);
      outtextxy(x, y, "Register");

int gdriver = DETECT, gmode;
   int x1 = 10, y1 = 70;
   int x2 = 800, y2 = 70;
 line(x1, y1, x2, y2);

  y+=76;

  cout<<endl<<endl<<endl<<endl<<endl<<endl<<endl<<"\t  ";

   settextstyle(font=6, HORIZ_DIR, 1);

  outtextxy(x, y, "Name:");

   gets(name);
 y+=32;
 cout<<endl<<"\t";

 outtextxy(x, y, "Add.: ");
   gets(adrs);
   getch();

randomize();
char ltr= 65 + rand()%5;
char i=48 + rand()%10;

char str[8]={ltr,ltr+1,'0',i};
   strcpy(id,str);

 y+=32;
 cout<<endl<<"\t";

 outtextxy(x, y, "ID: ");
   puts(id);
   getch();

   closegraph();
   getch();
   clrscr();


}
void student::billgen()//second input...
{



}

void student::output()
{
cout<<"Name is "<<name<<endl;
cout<<"adrs is "<<adrs<<endl<<""<<id;
}

int R,ch,flag,pos;
char N[20],ans;
float M;
fstream f1,f2;
student s1;
void addrecord();
void displayrecord();

void main()
{
clrscr();
do
{
cout<<"Press 1 to add record"<<endl;
cout<<"Press 2 to display all records"<<endl;


cout<<"Enter your choice"<<endl;
cin>>ch;
switch(ch)
{
case 1:
addrecord();
break;
case 2:
displayrecord();
break;

break;
default:
cout<<"Invalid number entered"<<endl;
}
cout<<"Do you wish to continue"<<endl;
cin>>ans;
}while(ans=='y'||ans=='Y');
getch();
}
void addrecord()
{
f1.open("456.dat",ios::out|ios::app|ios::binary);
cout<<"Enter new information of student to add"<<endl;
s1.input();
f1.write((char *) &s1,sizeof(s1));
cout<<"Record added"<<endl;
f1.close();
}
void displayrecord()
{
f1.open("456.dat",ios::in|ios::binary);
f1.read((char *) &s1,sizeof(s1));
while(!f1.eof())
{
s1.output();
f1.read((char *) &s1,sizeof(s1));
}
f1.close();
}


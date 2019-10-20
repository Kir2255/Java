drop database if exists TravelAgency;
create database TravelAgency;

use TravelAgency;

create table Customers (
	CustomerID int primary key auto_increment,
    FIO nvarchar(20) not null,
    RegularClient bool
);

create table TourTypes (
	TourID int primary key auto_increment,
    Type nvarchar(10) not null,
    HotTour bool
);

create table TravelAgents (
	TravelAgentID int primary key auto_increment,
    FIO nvarchar(20) not null
);

create table TravelLists (
	TravelListID int primary key auto_increment,
    TourID int not null,
    CustomerID int not null,
    TravelAgentID int not null,
    Cost double,
    Sale double check(Sale > 0 and Sale < 100)
);

alter table TravelLists add constraint fk_TravelLists_to_TourTypes foreign key(TourID) references TourTypes (TourID) on delete cascade;
alter table TravelLists add constraint fk_TravelLists_to_Customers foreign key(CustomerID) references Customers (CustomerID) on delete cascade;
alter table TravelLists add constraint fk_TravelLists_to_TravelAgents foreign key(TravelAgentID) references TravelAgents (TravelAgentId) on delete cascade;


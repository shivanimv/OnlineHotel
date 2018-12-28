package com.onlinehotel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.onlinehotel.exception.OnlineHotelException;
import com.onlinehotel.model.BookingDetails;
import com.onlinehotel.model.BookingEntry;
import com.onlinehotel.model.Customer;
import com.onlinehotel.model.Hotel;
import com.onlinehotel.model.Location;
import com.onlinehotel.model.Room;
import com.onlinehotel.model.RoomType;
import com.onlinehotel.util.ConnectionUtil;

public class CustomerDAO {

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}

	public boolean loginCheck(Customer customer) throws OnlineHotelException{
		boolean flag = false;
		String query = "select * from customer where phone_no=? and password=?";
		Connection conobj = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			conobj = ConnectionUtil.getConnection();
			pstmt = (PreparedStatement) conobj.prepareStatement(query);
			pstmt.setString(1,customer.getPhoneNo());
			pstmt.setString(2,customer.getPassword());
			result = pstmt.executeQuery();
			if (result.next()) {
				customer.setCustName(result.getString("cust_name"));
				flag = true;
			}
		} catch (SQLException e) {
			
			throw new OnlineHotelException("Customerdao has problem"+e);
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conobj != null) {
					conobj.close();
				}
			} catch (SQLException e) {
				System.out.println("error while closing object");
			}
		}

		return flag;
	}
	
	public boolean registerCustomer(Customer customer) throws OnlineHotelException
	{
		boolean flag=false;
		String query="insert into customer values(?,?,?)";
		Connection conobj = null;
		PreparedStatement pstmt=null;
		try {
			conobj = ConnectionUtil.getConnection();
			pstmt=(PreparedStatement) conobj.prepareStatement(query);
			pstmt.setString(1,customer.getPhoneNo());
			pstmt.setString(2,customer.getCustName());
			pstmt.setString(3, customer.getPassword());
			pstmt.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new OnlineHotelException("Customerdao has problem"+e);
		}
		finally{
			try{
				
				if(pstmt!=null)
				{
					pstmt.close();
				}
				if (conobj != null) {
					conobj.close();
				}
			}
			catch(SQLException e)
			{
				System.out.println("error while closing object");
			}
			}
	return flag;
	}
 
	public List<BookingDetails> listAllBooking(Customer customer) throws OnlineHotelException {
		Connection conobj = null;
		PreparedStatement pstmt = null;
		String query = "select l.location_name,h.hotel_name,r.room_id,rt.type,be.bid,be.booking_date,bd.check_in,bd.check_out from bookingentry be inner join bookingdetails bd inner join customer c inner join room r inner join hotel h inner join location l inner join roomtype rt on be.bid=bd.BookingEntry_bid and be.Customer_phone_no=c.phone_no and r.room_id=bd.Room_room_id and r.Hotel_hotel_id=h.hotel_id and l.location_id=h.Location_location_id and r.RoomType_type_id=rt.type_id where c.phone_no=?";
		ResultSet result = null;
		List<BookingDetails> bookinglist = new ArrayList<BookingDetails>();

		try {
		conobj = ConnectionUtil.getConnection();
		pstmt = conobj.prepareStatement(query);
		pstmt.setString(1, customer.getPhoneNo());
		result = pstmt.executeQuery();
		while (result.next()) {
		BookingDetails bookingDetails = new BookingDetails();
		Location location = new Location();
		location.setLocationName(result.getString(1));

		Hotel hotel = new Hotel();
		hotel.setLocation(location);
		hotel.setHotelName(result.getString(2));

		Room room = new Room();
		room.setHotelId(hotel);
		room.setRoomId(result.getInt(3));
		RoomType roomType=new RoomType();
		roomType.setType(result.getString(4));
		room.setRoomTypeId(roomType);

		BookingEntry bookingEntry = new BookingEntry();
		bookingEntry.setCustomerPhNo(customer);
		bookingEntry.setBid(result.getInt(5));
		bookingEntry.setBookingDate(result.getDate(6));

		bookingDetails.setCheckIn(result.getDate(7));
		bookingDetails.setCheckOut(result.getDate(8));
		bookingDetails.setRoomId(room);
		bookingDetails.setBid(bookingEntry);
		

		bookinglist.add(bookingDetails);
		}

		} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Customer dao has problem" + e);
		throw new OnlineHotelException();
		} finally {
		try {
		if (result != null) {
		result.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conobj != null) {
		conobj.close();
		}
		} catch (SQLException e) {
		System.out.println("error while closing object");
		}
		}
		return bookinglist;
		}

}

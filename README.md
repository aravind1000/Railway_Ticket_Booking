# Train Reservation System

This is a command-line application for booking tatkal reservations for trains. The application allows users to book, cancel, and view the status of their tickets. It manages concurrent bookings and maintains a waiting list for tickets.

## Features

- Book tickets for specific trains.
- Cancel tickets and manage the waiting list.
- View ticket status including PNR number and current status (Confirmed or Waiting List).

## Train Details

The application uses the following default train information:

| Train No | Train Name     | Boarding Point | Destination | Departure Time | Max Available |
|----------|----------------|----------------|-------------|----------------|---------------|
| 1        | Kovai SF EX    | Chennai        | Coimbatore  | 10 PM          | 5             |
| 2        | Ernakulam EX   | Chennai        | Coimbatore  | 11 PM          | 7             |
| 3        | Coimbatore EX  | Chennai        | Coimbatore  | 9 PM           | 3             |

## User Inputs

The application prompts the user for the following inputs:

1. Name
2. Age
3. Boarding Point
4. Destination Point
5. Train Number

## Application Functionalities

1. **Book Ticket**: Books a ticket for a specific train and provides a PNR number.
2. **Cancel Ticket**: Cancels a booked ticket and manages the waiting list.
3. **View Ticket Status**: Displays the status of the booked tickets including PNR and current status.

## Usage

1. Clone the repository:
    ```bash
    git clone https://github.com/aravind1000/Railway_Ticket_Booking.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Railway_Ticket_Booking
    ```

3. Run the application:
    ```bash
    python Railway.py
    ```





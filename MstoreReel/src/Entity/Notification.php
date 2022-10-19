<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Notification
 *
 * @ORM\Table(name="notification")
 * @ORM\Entity
 */
class Notification
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="emailtoreply", type="string", length=250)
     */
    private $emailtoreply;


    public function __construct()
    {
        $this->emailtoreply = new ArrayCollection();;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getEmailtoreply() : ?string
    {
        return $this->emailtoreply;
    }

    /**
     * @param string $emailtoreply
     */
    public function setEmailtoreply($emailtoreply): void
    {
        $this->emailtoreply = $emailtoreply;
    }


}
